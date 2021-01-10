package net.shyshkin.study.apigateway;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import net.shyshkin.study.apigateway.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SamplePetsServiceClientTest {

    private SamplePetsService client;

    @BeforeEach
    void setUp() {
        client = SamplePetsService.builder()
                .connectionConfiguration(new ConnectionConfiguration()
                        .maxConnections(100)
                        .connectionMaxIdleMillis(1000))
                .timeoutConfiguration(new TimeoutConfiguration()
                        .httpRequestTimeout(3000)
                        .totalExecutionTimeout(10000)
                        .socketTimeout(2000))
                .build();
    }

    @AfterEach
    void tearDown() {
        client.shutdown();
    }

    @Test
    void getRootTest() {
        //when
        GetApiRootResult rootResult = client.getApiRoot(new GetApiRootRequest());

        //then
        System.out.println(rootResult);
        assertNotNull(rootResult);
    }

    @Test
    void getPetsTest() {
        //when
        GetPetsResult petsResult = client.getPets(new GetPetsRequest());

        //then
        System.out.println(petsResult);
        assertNotNull(petsResult);
        assertThat(petsResult.getPets())
                .hasSize(3)
                .allSatisfy(pet -> assertThat(pet).hasNoNullFieldsOrProperties());
    }

    @Test
    void getPetById() {
        //when
        GetPetResult petResult = client.getPet(new GetPetRequest().petId("1"));

        //then
        System.out.println(petResult);
        assertNotNull(petResult);
        assertThat(petResult.getPet())
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id",1);
    }
}