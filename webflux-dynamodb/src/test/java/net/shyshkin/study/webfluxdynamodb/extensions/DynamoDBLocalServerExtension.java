package net.shyshkin.study.webfluxdynamodb.extensions;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DynamoDBLocalServerExtension implements AfterAllCallback, BeforeAllCallback {

    private DynamoDBProxyServer server;

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        server.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        System.setProperty("sqlite4java.library.path", "target/native-libs");
        String port = "8000";
        server = ServerRunner.createServerFromCommandLineArgs(
                new String[]{"-inMemory", "-port", port});
        server.start();
    }
}
