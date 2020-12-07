package com.aws.codestar.projecttemplates.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link HelloWorldController}. Modify the tests in order to support your use case as you build your project.
 */
@DisplayName("Tests for HelloWorldController")
public class HelloWorldControllerTest {

    private static final String EXPECTED_RESPONSE_VALUE = "Hello AWS CodeStar!";
    private static final String INPUT_NAME = "AWS CodeStar";

    private final HelloWorldController controller = new HelloWorldController();

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
    @Test
    @DisplayName("Basic test for GET request")
    void testGetRequest() {
        ResponseEntity responseEntity = controller.helloWorldGet(INPUT_NAME);

        // Verify the response obtained matches the values we expect.
        JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
        assertEquals(EXPECTED_RESPONSE_VALUE, jsonObjectFromResponse.get("Output"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * Basic test to verify the result obtained when calling {@link HelloWorldController#helloWorldPost} successfully.
     */
    @Test
    @DisplayName("Basic test for POST request")
    void testPostRequest() {
        ResponseEntity responseEntity = controller.helloWorldPost(INPUT_NAME);

        // Verify the response obtained matches the values we expect.
        JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
        assertEquals(EXPECTED_RESPONSE_VALUE, jsonObjectFromResponse.get("Output"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
