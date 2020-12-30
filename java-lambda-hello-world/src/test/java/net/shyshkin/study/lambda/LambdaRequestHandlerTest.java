package net.shyshkin.study.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SystemStubsExtension.class)
class LambdaRequestHandlerTest {

    @SystemStub
    private EnvironmentVariables environment;

    LambdaRequestHandler handler = new LambdaRequestHandler();

    @BeforeEach
    void setUp() {
        environment.set("PARALLELISM", "8");
    }

    @Test
    void handleRequest() {
        //given
        String input = "Hello local";

        //when
        Context context = new TestContext();
        String output = handler.handleRequest(input, context);

        //then
        assertTrue(output.contains("Input is"));
        System.out.println(output);
    }
}