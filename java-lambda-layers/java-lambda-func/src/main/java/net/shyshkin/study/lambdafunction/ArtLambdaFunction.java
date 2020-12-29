package net.shyshkin.study.lambdafunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import net.shyshkin.study.lambdalayer.LambdaAbstractWrapper;

import java.util.List;
import java.util.Map;

public class ArtLambdaFunction extends LambdaAbstractWrapper<String> {
    @Override
    protected String processMessage(String messageBody) throws Exception {
        return messageBody.toUpperCase() + "_OK";
    }

    @Override
    public List<String> handleRequest(Map<String, Object> input, Context context) {
        List<String> resultList = super.handleRequest(input, context);
        LambdaLogger logger = context.getLogger();
        resultList.forEach(logger::log);
        return resultList;
    }
}
