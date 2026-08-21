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
package com.iemr.common.bengen.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.ParseException;

import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;
import com.iemr.common.bengen.utils.exception.IEMRException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("OutputResponse.Builder Test Suite")
class OutputResponseBuilderTest {

    private JsonObject build(OutputResponse.Builder builder) {
        return builder.build().getResponse().getAsJsonObject();
    }

    @Nested
    @DisplayName("Fluent field assembly")
    class FieldAssemblyTests {

        @Test
        @DisplayName("build should default every field to an empty value")
        void build_shouldDefaultEveryFieldToEmptyValue() {
            JsonObject response = build(new OutputResponse.Builder());

            assertEquals("", response.get("methodName").getAsString());
            assertEquals("", response.get("dataObjectType").getAsString());
            assertEquals("", response.get("dataJsonType").getAsString());
            assertEquals("", response.get("data").getAsString());
            assertEquals(0, response.get("statusCode").getAsInt());
            assertEquals("", response.get("statusMessage").getAsString());
            assertEquals("", response.get("statusMessageLong").getAsString());
        }

        @Test
        @DisplayName("build should carry every explicitly set field into the response")
        void build_shouldCarryEverySetFieldIntoResponse() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setMethodName("generateBeneficiaryIDs")
                    .setDataObjectType("BeneficiaryId")
                    .setDataJsonType("array")
                    .setData("[1,2,3]")
                    .setStatusCode(OutputResponse.Builder.SUCCESS)
                    .setStatusMessage("Success")
                    .setStatusMessageLong("Generated successfully"));

            assertEquals("generateBeneficiaryIDs", response.get("methodName").getAsString());
            assertEquals("BeneficiaryId", response.get("dataObjectType").getAsString());
            assertEquals("array", response.get("dataJsonType").getAsString());
            assertEquals("[1,2,3]", response.get("data").getAsString());
            assertEquals(200, response.get("statusCode").getAsInt());
            assertEquals("Success", response.get("statusMessage").getAsString());
            assertEquals("Generated successfully", response.get("statusMessageLong").getAsString());
        }

        @Test
        @DisplayName("each setter should return the same builder so calls can be chained")
        void setters_shouldReturnSameBuilderForChaining() {
            OutputResponse.Builder builder = new OutputResponse.Builder();

            assertEquals(builder, builder.setMethodName("m"));
            assertEquals(builder, builder.setDataObjectType("o"));
            assertEquals(builder, builder.setDataJsonType("object"));
            assertEquals(builder, builder.setData("{}"));
            assertEquals(builder, builder.setStatusCode(200));
            assertEquals(builder, builder.setStatusMessage("s"));
            assertEquals(builder, builder.setStatusMessageLong("l"));
            assertEquals(builder, builder.setErrorMessage(new IEMRException("x")));
        }

        @Test
        @DisplayName("toString should serialise only the exposed response field")
        void toString_shouldSerialiseOnlyExposedResponseField() {
            String json = new OutputResponse.Builder()
                    .setMethodName("generateBeneficiaryIDs")
                    .setStatusCode(OutputResponse.Builder.SUCCESS)
                    .build()
                    .toString();

            assertTrue(json.startsWith("{\"response\":"));
            assertTrue(json.contains("generateBeneficiaryIDs"));
            assertTrue(json.contains("\"statusCode\":200"));
        }
    }

    @Nested
    @DisplayName("setErrorMessage mapped from a throwable")
    class ErrorMappingTests {

        @Test
        @DisplayName("setErrorMessage should map IEMRException to a user login failure")
        void setErrorMessage_shouldMapIemrExceptionToUserIdFailure() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new IEMRException("invalid credentials")));

            assertEquals(OutputResponse.Builder.USERID_FAILURE, response.get("statusCode").getAsInt());
            assertEquals("User login failed", response.get("statusMessage").getAsString());
            assertEquals("invalid credentials", response.get("statusMessageLong").getAsString());
        }

        @Test
        @DisplayName("setErrorMessage should map JSONException to an object conversion failure")
        void setErrorMessage_shouldMapJsonExceptionToObjectFailure() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new JSONException("bad json")));

            assertEquals(OutputResponse.Builder.OBJECT_FAILURE, response.get("statusCode").getAsInt());
            assertEquals("Invalid object conversion", response.get("statusMessage").getAsString());
        }

        @Test
        @DisplayName("setErrorMessage should map SQLException to a code exception")
        void setErrorMessage_shouldMapSqlExceptionToCodeException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new SQLException("deadlock")));

            assertEquals(OutputResponse.Builder.CODE_EXCEPTION, response.get("statusCode").getAsInt());
            assertTrue(response.get("statusMessage").getAsString().startsWith("Failed with critical errors at "));
            assertEquals("deadlock", response.get("statusMessageLong").getAsString());
        }

        @Test
        @DisplayName("setErrorMessage should map ParseException to a code exception")
        void setErrorMessage_shouldMapParseExceptionToCodeException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new ParseException("bad date", 0)));

            assertEquals(OutputResponse.Builder.CODE_EXCEPTION, response.get("statusCode").getAsInt());
        }

        @Test
        @DisplayName("setErrorMessage should map NullPointerException to a code exception")
        void setErrorMessage_shouldMapNullPointerExceptionToCodeException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new NullPointerException("npe")));

            assertEquals(OutputResponse.Builder.CODE_EXCEPTION, response.get("statusCode").getAsInt());
        }

        @Test
        @DisplayName("setErrorMessage should map ArrayIndexOutOfBoundsException to a code exception")
        void setErrorMessage_shouldMapArrayIndexExceptionToCodeException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new ArrayIndexOutOfBoundsException("index 5")));

            assertEquals(OutputResponse.Builder.CODE_EXCEPTION, response.get("statusCode").getAsInt());
        }

        @Test
        @DisplayName("setErrorMessage should map IOException to an environment exception")
        void setErrorMessage_shouldMapIoExceptionToEnvironmentException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new IOException("disk full")));

            assertEquals(OutputResponse.Builder.ENVIRONMENT_EXCEPTION, response.get("statusCode").getAsInt());
            assertTrue(response.get("statusMessage").getAsString().startsWith("Failed with connection issues at "));
        }

        @Test
        @DisplayName("setErrorMessage should map ConnectException to an environment exception")
        void setErrorMessage_shouldMapConnectExceptionToEnvironmentException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new ConnectException("refused")));

            assertEquals(OutputResponse.Builder.ENVIRONMENT_EXCEPTION, response.get("statusCode").getAsInt());
        }

        @Test
        @DisplayName("setErrorMessage should fall back to a generic failure for an unmapped exception")
        void setErrorMessage_shouldFallBackToGenericFailureForUnmappedException() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new IllegalStateException("something odd")));

            assertEquals(OutputResponse.Builder.GENERIC_FAILURE, response.get("statusCode").getAsInt());
            assertEquals("Failed with generic exception", response.get("statusMessage").getAsString());
            assertEquals("something odd", response.get("statusMessageLong").getAsString());
        }
    }
}
