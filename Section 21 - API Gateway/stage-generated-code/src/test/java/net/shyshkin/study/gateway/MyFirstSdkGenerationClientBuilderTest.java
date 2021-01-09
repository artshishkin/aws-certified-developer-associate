package net.shyshkin.study.gateway;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import net.shyshkin.study.gateway.model.GetApiRootRequest;
import net.shyshkin.study.gateway.model.GetApiRootResult;
import net.shyshkin.study.gateway.model.GetHousesRequest;
import net.shyshkin.study.gateway.model.GetHousesResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MyFirstSdkGenerationClientBuilderTest {

    private MyFirstSdkGeneration client;

    @BeforeEach
    void setUp() {
        client = MyFirstSdkGeneration.builder()
                .connectionConfiguration(
                        new ConnectionConfiguration()
                                .maxConnections(100)
                                .connectionMaxIdleMillis(1000))
                .timeoutConfiguration(
                        new TimeoutConfiguration()
                                .httpRequestTimeout(3000)
                                .totalExecutionTimeout(10000)
                                .socketTimeout(2000))
                .build();;
    }

    @AfterEach
    void tearDown() {
        client.shutdown();
    }

    @Test
    void testRootRequest() {
        //when
        GetApiRootResult result = client.getApiRoot(new GetApiRootRequest());

        //then
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void testHousesRequest() {
        //when
        GetHousesResult result = client.getHouses(new GetHousesRequest());

        //then
        System.out.println(result);
        assertNotNull(result);
    }

}