package net.shyshkin.study.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.handlers.TracingHandler;

import java.util.List;

public class LambdaRequestHandler implements RequestHandler<String, String> {

    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRequestHandlers(new TracingHandler(AWSXRay.getGlobalRecorder()))
            .build();

    @Override
    public String handleRequest(String input, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Input: " + input);
        List<Bucket> buckets = s3Client.listBuckets();
        String parallelism = System.getenv("PARALLELISM");
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","10");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", parallelism);

        buckets.parallelStream()
                .peek(bucket -> logger.log("bucket location: " + s3Client.getBucketLocation(bucket.getName())))
                .filter(bucket -> s3Client.getBucketLocation(bucket.getName()).equalsIgnoreCase("eu-north-1"))
                .map(bucket -> String.format("[bucket: %s] objects count %d", bucket.getName(), s3Client.listObjectsV2(bucket.getName()).getKeyCount()))
                .forEach(logger::log);
        return "Hello World - " + input + " parallelism " + parallelism;
    }
}
