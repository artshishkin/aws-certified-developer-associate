package net.shyshkin.study.lambdafunction;

import net.shyshkin.study.lambdalayer.LambdaAbstractWrapper;

public class ArtLambdaFunction extends LambdaAbstractWrapper<String> {
    @Override
    protected String processMessage(String messageBody) throws Exception {
        return messageBody.toUpperCase()+"_OK";
    }
}
