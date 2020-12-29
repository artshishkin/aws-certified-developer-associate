package net.shyshkin.study.lambdalayer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class LambdaAbstractWrapper<O> implements RequestHandler<Map<String, Object>, List<O>> {

    public List<O> handleRequest(Map<String, Object> input, Context context) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> messages = (List<Map<String, Object>>) input.get("Records");
        List<O> responses = new ArrayList<O>();

        for (Map<String, Object> message : messages) {
            String messageBody = (String) message.get("body");

            context.getLogger().log(messageBody);

            try {
                O response = processMessage(messageBody);
                responses.add(response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return responses;
    }

    protected abstract O processMessage(String messageBody) throws Exception;
}
