package net.shyshkin.study.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.utils.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class LambdaRequestHandler implements RequestHandler<String, String> {

    S3AsyncClient s3Client = S3AsyncClient.builder()
//            . withRequestHandlers(new TracingHandler(AWSXRay.getGlobalRecorder()))
            .build();

    @Override
    public String handleRequest(String input, Context context) {

        String parallelism = System.getenv("PARALLELISM");
        if (StringUtils.isEmpty(parallelism)) parallelism = "10";
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","10");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", parallelism);

        LambdaLogger logger = context.getLogger();
        logger.log("Input: " + input);
        CompletableFuture<ListBucketsResponse> listBucketsCF = s3Client.listBuckets();
        List<String> join = listBucketsCF
                .thenApply(listBucketsResponse -> {
                    List<Bucket> buckets = listBucketsResponse.buckets();

                    List<CompletableFuture<String>> bucketSizes = buckets
                            .stream()
//                            .filter(bucket -> bucket.name().contains("art-kate"))
                            .map(
                                    bucket -> s3Client
                                            .getBucketLocation(builder -> builder.bucket(bucket.name()))
                                            .thenApply(response -> {
                                                        String regionName = response.locationConstraint().name();
                                                        logger.log("Region Name: `" + regionName + "`");
                                                        return regionName;
                                                    }
                                            )
                                            .thenCompose(regionName -> {
                                                if ("eu_north_1".equalsIgnoreCase(regionName)) {
                                                    return s3Client.listObjectsV2(builder -> builder.bucket(bucket.name()))
                                                            .thenApply(ListObjectsV2Response::keyCount)
                                                            .thenApply(keyCount -> String.format("[Bucket `%s` has %d keys]", bucket.name(), keyCount))
                                                            .whenCompleteAsync((message, ex) -> logger.log(message));
                                                }
                                                return CompletableFuture.supplyAsync(() -> bucket.name() + " is in another region " + regionName);
                                            })
                            )
                            .collect(Collectors.toList());

                    return bucketSizes.stream().map(CompletableFuture::join).collect(Collectors.toList());
                })
                .join();

        return "Input is - " + input + "; parallelism " + parallelism + "; Bucket objects count info: " + String.join("|", join);
    }
}
