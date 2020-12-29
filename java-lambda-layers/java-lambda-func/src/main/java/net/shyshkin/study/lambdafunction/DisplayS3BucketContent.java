package net.shyshkin.study.lambdafunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.handlers.TracingHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.shyshkin.study.lambdalayer.LambdaAbstractWrapper;

import java.util.List;
import java.util.Map;

public class DisplayS3BucketContent extends LambdaAbstractWrapper<Bucket> {

//    Logger log = LoggerFactory.getLogger(DisplayS3BucketContent.class);

    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRequestHandlers(new TracingHandler(AWSXRay.getGlobalRecorder()))
            .build();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private LambdaLogger logger;

    @Override
    protected Bucket processMessage(String messageBody) throws Exception {

        Bucket bucket = gson.fromJson(messageBody, Bucket.class);
        ListObjectsV2Result listObjectsV2Result = s3Client.listObjectsV2(bucket.getName());
        String objectList = gson.toJson(listObjectsV2Result);
//        log.info("{}", objectList);
        logger.log(objectList);
        return bucket;
    }

    @Override
    public List<Bucket> handleRequest(Map<String, Object> input, Context context) {
        logger = context.getLogger();
        return super.handleRequest(input, context);
    }
}
