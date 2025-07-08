/*
* AMRIT - Accessible Medical Records via Integrated Technologies
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.common.bengen.controller;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.iemr.common.bengen.domain.M_BeneficiaryRegidMapping;
import com.iemr.common.bengen.service.GenerateBeneficiaryService;
import com.iemr.common.bengen.utils.OutputResponse;
import com.iemr.common.bengen.utils.mapper.InputMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

@DisplayName("GenerateBeneficiaryController Test Suite")
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

        List<HandlerExceptionResolver> exceptionResolvers = new ArrayList<>();
        exceptionResolvers.add(new CustomTestExceptionResolver());

        mockMvc = MockMvcBuilders.standaloneSetup(generateBeneficiaryController)
                .setHandlerExceptionResolvers(exceptionResolvers)
                .build();

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
        String malformedJson = "{ \"benIDRequired\": \"not_a_long\", \"vanID\": 101 }";

        when(generateBeneficiaryService.getBeneficiaryIDs(isNull(), anyInt()))
                .thenThrow(new NullPointerException("benIDRequired cannot be null in service (simulated)"));

        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Should return 500 when missing required input leads to service NullPointerException")
    void getBeneficiaryIDs_missingInputCausesServiceNullPointer() throws Exception {
        String missingFieldsJson = "{ \"vanID\": 101 }";

        when(generateBeneficiaryService.getBeneficiaryIDs(isNull(), anyInt()))
                .thenThrow(new NullPointerException("benIDRequired cannot be null in service (simulated)"));

        mockMvc.perform(post("/generateBeneficiaryController/generateBeneficiaryIDs")
                        .header("Authorization", "Bearer dummy_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(missingFieldsJson))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Should handle service throwing a generic runtime exception (HTTP 500)")
    void getBeneficiaryIDs_serviceException() throws Exception {
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

    private static class RequestInput {
        private Long benIDRequired;
        private Integer vanID;

        public RequestInput(Long benIDRequired, Integer vanID) {
            this.benIDRequired = benIDRequired;
            this.vanID = vanID;
        }

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
     * Minimal, silent exception resolver for unit test purposes.
     */
    static class CustomTestExceptionResolver implements HandlerExceptionResolver {
        @Override
        public ModelAndView resolveException(
                HttpServletRequest request,
                HttpServletResponse response,
                @Nullable Object handler,
                Exception ex) {

            if (ex instanceof HttpMessageNotReadableException) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return new ModelAndView();
            } else if (ex instanceof RuntimeException) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ModelAndView();
            }

            return null; // Let other resolvers handle
        }
    }

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
