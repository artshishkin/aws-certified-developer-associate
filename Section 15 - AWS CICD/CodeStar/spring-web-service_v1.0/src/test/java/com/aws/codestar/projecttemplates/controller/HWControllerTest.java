package com.aws.codestar.projecttemplates.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link HelloWorldController}. Modify the tests in order to support your use case as you build your project.
 */
@DisplayName("Tests for HelloWorldController")
@WebMvcTest(HWController.class)
public class HWControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * Initializing variables before we run the tests.
     * Use @BeforeAll for initializing static variables at the start of the test class execution.
     * Use @BeforeEach for initializing variables before each test is run.
     */
    @BeforeAll
    static void setup() {
        // Use as needed.
    }

    /**
     * De-initializing variables after we run the tests.
     * Use @AfterAll for de-initializing static variables at the end of the test class execution.
     * Use @AfterEach for de-initializing variables at the end of each test.
     */
    @AfterAll
    static void tearDown() {
        // Use as needed.
    }

    /**
     * Basic test to verify the result obtained when calling {@link HelloWorldController#helloWorldGet} successfully.
     */
    @ParameterizedTest
    @DisplayName("Basic test for GET request")
    @ValueSource(strings = {"GET", "POST"})
    void testGetRequest(HttpMethod httpMethod) throws Exception {
        //given
        String expectedResponse = "{\"Output\":\"Hello World! From updated Controller\"}";

        //when
        mockMvc.perform(request(httpMethod, "/").accept(APPLICATION_JSON))

                //then
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    /**
     * Basic test to verify the result obtained when calling {@link HelloWorldController#helloWorldGet} successfully.
     */
    @ParameterizedTest
    @DisplayName("Test for GET and POST request with parameters")
    @ValueSource(strings = {"GET", "POST"})
    void testRequest_withParameters(HttpMethod httpMethod) throws Exception {
        //given
        String expectedResponse = "{\"Output\":\"Hello AWS CodeStar! From updated Controller\"}";

        //when
        mockMvc
                .perform(
                        request(httpMethod, "/")
                                .param("name","AWS CodeStar")
                                .accept(APPLICATION_JSON))

                //then
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}
