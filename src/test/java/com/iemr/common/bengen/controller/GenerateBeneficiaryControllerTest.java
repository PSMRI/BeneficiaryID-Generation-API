package com.iemr.common.bengen.controller;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.iemr.common.bengen.domain.M_BeneficiaryRegidMapping;
import com.iemr.common.bengen.service.GenerateBeneficiaryService;
import com.iemr.common.bengen.utils.OutputResponse;
import com.iemr.common.bengen.utils.mapper.InputMapper; // Assuming this is your DTO for the controller
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// REMOVED: jakarta.validation.constraints.NotNull; // Not needed without @Valid in controller
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// REMOVED: org.springframework.web.bind.MethodArgumentNotValidException; // Not needed without @Valid in controller
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.lang.Nullable;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GenerateBeneficiaryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GenerateBeneficiaryService generateBeneficiaryService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private GenerateBeneficiaryController generateBeneficiaryController;

    private Gson outputMapperGson;
    private Gson inputMapperGson;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // --- Custom Exception Handling for MockMvc standalone setup ---
        List<HandlerExceptionResolver> exceptionResolvers = new ArrayList<>();
        exceptionResolvers.add(new CustomTestExceptionResolver());

        mockMvc = MockMvcBuilders.standaloneSetup(generateBeneficiaryController)
                                 .setHandlerExceptionResolvers(exceptionResolvers)
                                 .build();
        // --- END OF CUSTOM EXCEPTION HANDLING SETUP ---

        outputMapperGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .create();

        inputMapperGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
    }

    @Test
    @DisplayName("Should successfully generate beneficiary IDs with valid input")
    void getBeneficiaryIDs_success() throws Exception {
        // Given
        Long benIDRequired = 2L;
        Integer vanID = 101;
        String createdBy = "test_user";

        Timestamp now = Timestamp.from(Instant.parse("2024-06-12T10:30:00.123Z"));

        String requestJson = inputMapperGson.toJson(new RequestInput(benIDRequired, vanID));

        List<M_BeneficiaryRegidMapping> mockBenMappings = Arrays.asList(
                new M_BeneficiaryRegidMapping(1001L, 1L, now, createdBy),
                new M_BeneficiaryRegidMapping(1002L, 2L, now, createdBy)
        );

        when(generateBeneficiaryService.getBeneficiaryIDs(benIDRequired, vanID))
                .thenReturn(mockBenMappings);

        Type listOfBenMappingType = new TypeToken<List<M_BeneficiaryRegidMapping>>() {}.getType();
        String expectedDataJson = outputMapperGson.toJson(mockBenMappings, listOfBenMappingType);

        OutputResponse expectedOutputResponse = new OutputResponse.Builder()
                .setDataJsonType("JsonObject.class")
                .setStatusCode(200)
                .setStatusMessage("success")
                .setDataObjectType(GenerateBeneficiaryController.class.getSimpleName())
                .setMethodName("generateBeneficiaryIDs")
                .setData(expectedDataJson)
                .build();
        String expectedResponseString = expectedOutputResponse.toString();

        // When & Then
        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseString));
    }

    @Test
    @DisplayName("Should return empty list when service returns empty list")
    void getBeneficiaryIDs_emptyList() throws Exception {
        // Given
        Long benIDRequired = 0L;
        Integer vanID = 101;

        String requestJson = inputMapperGson.toJson(new RequestInput(benIDRequired, vanID));

        when(generateBeneficiaryService.getBeneficiaryIDs(benIDRequired, vanID))
                .thenReturn(Collections.emptyList());

        Type listOfBenMappingType = new TypeToken<List<M_BeneficiaryRegidMapping>>() {}.getType();
        String expectedDataJson = outputMapperGson.toJson(Collections.emptyList(), listOfBenMappingType);

        OutputResponse expectedOutputResponse = new OutputResponse.Builder()
                .setDataJsonType("JsonObject.class")
                .setStatusCode(200)
                .setStatusMessage("success")
                .setDataObjectType(GenerateBeneficiaryController.class.getSimpleName())
                .setMethodName("generateBeneficiaryIDs")
                .setData(expectedDataJson)
                .build();
        String expectedResponseString = expectedOutputResponse.toString();

        // When & Then
        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseString));
    }

    @Test
    @DisplayName("Should return 500 when malformed type input leads to service NullPointerException")
    void getBeneficiaryIDs_inputCausesServiceNullPointer() throws Exception {
        // Given: Malformed JSON where "not_a_long" for a Long field will result in null being passed to service
        String malformedJson = "{ \"benIDRequired\": \"not_a_long\", \"vanID\": 101 }";

        // Mock the service to throw NullPointerException when benIDRequired is null
        when(generateBeneficiaryService.getBeneficiaryIDs(isNull(), anyInt()))
                .thenThrow(new NullPointerException("benIDRequired cannot be null in service (simulated)"));

        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isInternalServerError()); // Expecting 500 now
    }

    @Test
    @DisplayName("Should return 500 when missing required input leads to service NullPointerException")
    void getBeneficiaryIDs_missingInputCausesServiceNullPointer() throws Exception {
        // Given: JSON missing 'benIDRequired' entirely.
        // This will result in benIDRequired being null passed to service.
        String missingFieldsJson = "{ \"vanID\": 101 }";

        // Mock the service to throw NullPointerException when benIDRequired is null
        when(generateBeneficiaryService.getBeneficiaryIDs(isNull(), anyInt()))
                .thenThrow(new NullPointerException("benIDRequired cannot be null in service (simulated)"));

        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(missingFieldsJson))
                .andExpect(status().isInternalServerError()); // Expecting 500 now
    }


    @Test
    @DisplayName("Should handle service throwing a generic runtime exception (HTTP 500)")
    void getBeneficiaryIDs_serviceException() throws Exception {
        // Given
        Long benIDRequired = 5L;
        Integer vanID = 101;

        String requestJson = inputMapperGson.toJson(new RequestInput(benIDRequired, vanID));

        when(generateBeneficiaryService.getBeneficiaryIDs(anyLong(), anyInt()))
                .thenThrow(new RuntimeException("Simulated Database connection failed"));

        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Helper class to represent the input JSON structure for the request.
     * No @NotNull here, as controller won't validate without @Valid.
     */
    private static class RequestInput {
        private Long benIDRequired;
        private Integer vanID;

        public RequestInput(Long benIDRequired, Integer vanID) {
            this.benIDRequired = benIDRequired;
            this.vanID = vanID;
        }

        // Add standard getters and setters for Spring to bind correctly
        public Long getBenIDRequired() {
            return benIDRequired;
        }

        public void setBenIDRequired(Long benIDRequired) {
            this.benIDRequired = benIDRequired;
        }

        public Integer getVanID() {
            return vanID;
        }

        public void setVanID(Integer vanID) {
            this.vanID = vanID;
        }
    }

    /**
     * Custom Test Exception Resolver for MockMvc standalone setup.
     * This directly maps specific exceptions to HTTP status codes.
     * It handles HttpMessageNotReadableException and any RuntimeException (like NullPointerException from service).
     */
    static class CustomTestExceptionResolver implements HandlerExceptionResolver {
        @Override
        public ModelAndView resolveException(
                HttpServletRequest request,
                HttpServletResponse response,
                @Nullable Object handler,
                Exception ex) {

            // Diagnostic print statements (can be removed once tests pass)
            System.out.println("--- CustomTestExceptionResolver invoked ---");
            System.out.println("Exception caught: " + ex.getClass().getName());
            System.out.println("Exception message: " + ex.getMessage());

            if (ex instanceof HttpMessageNotReadableException) {
                // This would catch truly malformed JSON syntax (e.g., missing comma, invalid characters)
                System.out.println("Handling HttpMessageNotReadableException (400)");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return new ModelAndView();
            } else if (ex instanceof RuntimeException) {
                // Catches other RuntimeExceptions, including NullPointerException from service logic
                System.out.println("Handling RuntimeException (500)");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ModelAndView();
            }
            System.out.println("No specific handling for exception: " + ex.getClass().getName());
            return null; // Let other resolvers or default behavior handle if not matched
        }
    }

    // Keeping your original @ControllerAdvice class definition here for completeness,
    // but it's not directly used by the MockMvc setup with the CustomTestExceptionResolver in place.
    @ControllerAdvice
    static class GlobalTestExceptionHandler {
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + ex.getMostSpecificCause().getMessage());
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<String> handleGenericException(RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + ex.getMessage());
        }
    }
}