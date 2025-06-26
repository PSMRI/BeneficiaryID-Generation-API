package com.iemr.common.bengen.controller.version;

import com.fasterxml.jackson.databind.ObjectMapper; // Used for converting objects to JSON strings
import org.junit.jupiter.api.BeforeEach; // Annotation for setup method
import org.junit.jupiter.api.DisplayName; // Annotation for test display names
import org.junit.jupiter.api.Test; // Annotation for test methods
import org.junit.jupiter.api.extension.ExtendWith; // Annotation to integrate JUnit 5 with Mockito
import org.mockito.junit.jupiter.MockitoExtension; // Extension for Mockito integration
import org.springframework.test.web.servlet.MockMvc; // For performing HTTP requests and asserting responses
import org.springframework.test.web.servlet.setup.MockMvcBuilders; // Builder for MockMvc setup
import org.springframework.test.web.servlet.result.MockMvcResultHandlers; // For printing the response for debugging

import java.util.HashMap; // For creating a Map to represent the expected JSON object
import java.util.Map;   // For creating a Map to represent the expected JSON object

// No direct import for IOException needed in this version, as we don't mock throwing it from the private method.

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // Static import for GET request builder
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Static import for content matcher
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Static import for status matcher

/**
 * JUnit 5 test class for {@link VersionController}.
 * This test focuses on the public API of the controller.
 * It does NOT modify the controller's private methods for testing,
 * adhering to the "no controller change" constraint.
 * Instead, it relies on a `git.properties` file being present in
 * `src/test/resources` for the successful test case.
 */
@ExtendWith(MockitoExtension.class) // Enables Mockito annotations and features for JUnit 5
class VersionControllerTest {

    private MockMvc mockMvc; // MockMvc instance to simulate HTTP requests
    private ObjectMapper objectMapper; // Jackson ObjectMapper for serializing/deserializing JSON

    /**
     * Set up method executed before each test.
     * Initializes the controller and configures MockMvc.
     */
    @BeforeEach
    void setUp() {
        // Initialize the VersionController.
        // We are NOT spying on it here with Mockito because we cannot mock its private methods
        // at compile time without changing their visibility or using PowerMock.
        // Instead, we test it as a black box through its public API.
        VersionController versionController = new VersionController();

        // Build MockMvc instance for the VersionController.
        // standaloneSetup is used for focused testing of a single controller without loading the full Spring application context.
        mockMvc = MockMvcBuilders.standaloneSetup(versionController).build();

        // Initialize ObjectMapper to help create expected JSON strings for content assertions.
        objectMapper = new ObjectMapper();
    }

    /**
     * Test case to verify that the "/version" endpoint successfully returns
     * the content of "git.properties" wrapped in an {@code OutputResponse}.
     *
     * IMPORTANT: For this test to pass, you MUST have a `git.properties` file
     * in your project's `src/test/resources` directory.
     *
     * Example content for `src/test/resources/git.properties`:
     * ```properties
     * git.build.time=2025-06-25T10:00:00Z
     * git.commit.id=abcdef1234567890
     * git.branch=main
     * ```
     *
     * The `\n` characters are important to match how the controller reads lines.
     *
     * @throws Exception if any error occurs during the MockMvc operation.
     */
    @Test
    @DisplayName("Should return git.properties content when successfully read from test resources")
    void versionInformation_shouldReturnGitPropertiesContent() throws Exception {
        // Arrange
        // The content expected from the git.properties file in src/test/resources.
        // Ensure this matches the actual content of your test resource file exactly.
        String expectedGitPropertiesContent = "git.build.time=2025-06-25T10:00:00Z\n" +
                                              "git.commit.id=abcdef1234567890\n" +
                                              "git.branch=main\n"; // Note the trailing newline often present when read line by line

        // Create the expected inner JSON object that goes into the 'data' field.
        // Based on your OutputResponse's setResponse logic: {"response": "..."}
        Map<String, String> innerDataMap = new HashMap<>();
        innerDataMap.put("response", expectedGitPropertiesContent);

        // Create an instance of the dummy OutputResponse class with the expected successful response.
        OutputResponse expectedOutput = new OutputResponse();
        // Set the 'data' field to the JSON object that the controller actually produces.
        expectedOutput.setData(innerDataMap);
        expectedOutput.setStatusCode(200); // Set expected success status code
        expectedOutput.setErrorMessage("Success"); // Set expected success error message
        expectedOutput.setStatus("Success"); // Set expected success status

        // Convert the expected OutputResponse object to its JSON string representation.
        String expectedJsonResponse = objectMapper.writeValueAsString(expectedOutput);

        // Act & Assert
        // Perform a GET request to the "/version" endpoint.
        mockMvc.perform(get("/version"))
                .andDo(MockMvcResultHandlers.print()) // Keep this enabled until the test passes successfully
                // Assert that the HTTP status code is OK (200).
                .andExpect(status().isOk())
                // Assert that the response content matches the expected JSON string.
                .andExpect(content().json(expectedJsonResponse));
    }

    // Removed the test case for `shouldHandleIOException` because:
    // 1. It attempted to mock a private method, which caused compilation errors without controller changes.
    // 2. Simulating resource loading failure (like `getResourceAsStream` returning null or causing IOException)
    //    for a private method without PowerMock is not feasible under the "no controller change" constraint.
    // If testing this error path is critical, consider:
    // a) Making the ClassLoader dependency injectable in VersionController.
    // b) Using PowerMock (a more complex testing library, generally a last resort).
}

/**
 * Dummy {@code OutputResponse} class for testing purposes.
 * This class mimics the essential structure and JSON serialization behavior
 * of the actual {@code com.iemr.common.bengen.utils.response.OutputResponse}
 * to enable standalone testing without needing the full dependency.
 *
 * IMPORTANT: This class's field names (data, statusCode, errorMessage, status)
 * and their corresponding getters/setters have been adjusted to precisely match
 * the JSON keys and structure produced by your actual `OutputResponse` class.
 */
class OutputResponse {
    // These field names now precisely match the JSON keys produced by your actual OutputResponse class.
    private Object data; // This should be `Object` because it can hold a String, JsonArray, or JsonObject
    private int statusCode;
    private String errorMessage;
    private String status;

    // Adjust getters and setters to match the field names above.
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method is added only to mimic the `setError` behavior, if needed for future tests,
     * by setting the `errorMessage`, `status`, and `statusCode` fields.
     * It's not directly used in the current successful test case.
     * @param error The Throwable object representing the error.
     */
    public void setError(Throwable error) {
        this.errorMessage = error != null ? error.toString() : null;
        // Set default status and statusCode for error mimicking, adjust if your actual class has specific logic
        this.status = "FAILURE";
        this.statusCode = 5000; // GENERIC_FAILURE from your original class
    }


    /**
     * Overrides the {@code toString()} method to provide a JSON representation
     * of the {@code OutputResponse} object. This is crucial for {@code MockMvc}'s
     * {@code content().json()} matcher.
     *
     * @return A JSON string representing the {@code OutputResponse} object.
     */
    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // This will serialize the fields defined in this dummy class.
            // Ensure these fields align with your actual OutputResponse's JSON output.
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            // Fallback for serialization errors within the dummy class itself
            return "{ \"errorMessage\": \"Serialization failed in dummy OutputResponse\" }";
        }
    }
}
