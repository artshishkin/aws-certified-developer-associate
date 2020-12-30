package net.shyshkin.study.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LambdaRequestHandlerTest {

    LambdaRequestHandler handler = new LambdaRequestHandler();

    @Test
    void handleRequest() {
        //given
        String input = "Hello local";

        //when
        Context context = new TestContext();
        String output = handler.handleRequest(input, context);

        //then
        assertTrue(output.contains("Input is"));
    }
}