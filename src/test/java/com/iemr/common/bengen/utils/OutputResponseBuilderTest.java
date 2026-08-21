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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iemr.common.bengen.utils.exception.IEMRException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Nested
    @DisplayName("Value semantics of the built response")
    class ValueSemanticsTests {

        private OutputResponse response(String methodName) {
            return new OutputResponse.Builder().setMethodName(methodName).build();
        }

        @Test
        @DisplayName("two responses built from the same fields should be equal and share a hash code")
        void responsesFromSameFields_shouldBeEqual() {
            assertEquals(response("m"), response("m"));
            assertEquals(response("m").hashCode(), response("m").hashCode());
        }

        @Test
        @DisplayName("responses built from different fields should not be equal")
        void responsesFromDifferentFields_shouldNotBeEqual() {
            assertNotEquals(response("first"), response("second"));
        }

        @Test
        @DisplayName("a response should equal itself and never equal null or an unrelated type")
        void response_shouldEqualItselfAndNotOtherTypes() {
            OutputResponse built = response("m");

            assertEquals(built, built);
            assertNotEquals(built, null);
            assertNotEquals(built, "not an OutputResponse");
        }

        @Test
        @DisplayName("getResponse should expose the assembled JSON element")
        void getResponse_shouldExposeAssembledJsonElement() {
            JsonElement element = response("generateBeneficiaryIDs").getResponse();

            assertNotNull(element);
            assertEquals("generateBeneficiaryIDs",
                    element.getAsJsonObject().get("methodName").getAsString());
        }

        @Test
        @DisplayName("setResponse should replace the assembled payload")
        void setResponse_shouldReplaceAssembledPayload() {
            OutputResponse built = response("m");
            JsonObject replacement = new JsonObject();
            replacement.addProperty("methodName", "replaced");

            built.setResponse(replacement);

            assertEquals("replaced", built.getResponse().getAsJsonObject().get("methodName").getAsString());
        }

        @Test
        @DisplayName("a response with a null payload should differ from one carrying a payload")
        void responseWithNullPayload_shouldDifferFromPopulated() {
            OutputResponse built = response("m");
            OutputResponse blank = response("m");
            blank.setResponse(null);

            assertNotEquals(built, blank);
            assertNotEquals(blank, built);
            assertDoesNotThrow(blank::hashCode);
            assertDoesNotThrow(blank::toString);
        }
    }

    @Nested
    @DisplayName("Error mapping for exception types raised by other AMRIT modules")
    class ExternalExceptionMappingTests {

        // The mapping switches on getClass().getSimpleName(), so locally declared types
        // with the same simple names reach the arms meant for Hibernate/JDBC exceptions.
        private static class MissingMandatoryFieldsException extends Exception {
            MissingMandatoryFieldsException(String message) {
                super(message);
            }
        }

        private static class IllegalActionException extends Exception {
            IllegalActionException(String message) {
                super(message);
            }
        }

        private static class JDBCException extends Exception {
            JDBCException(String message) {
                super(message);
            }
        }

        private static class SQLGrammarException extends Exception {
            SQLGrammarException(String message) {
                super(message);
            }
        }

        private static class ConstraintViolationException extends Exception {
            ConstraintViolationException(String message) {
                super(message);
            }
        }

        @Test
        @DisplayName("setErrorMessage should map a missing mandatory field to the params-missing code")
        void setErrorMessage_shouldMapMissingMandatoryFields() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new MissingMandatoryFieldsException("benCount is required")));

            assertEquals(OutputResponse.Builder.MANDATORY_PARAMS_MISSING, response.get("statusCode").getAsInt());
            assertEquals("Missing Mandatory Parameters.", response.get("statusMessage").getAsString());
            assertEquals("benCount is required", response.get("statusMessageLong").getAsString());
        }

        @Test
        @DisplayName("setErrorMessage should map an illegal action to the illegal-action code")
        void setErrorMessage_shouldMapIllegalAction() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new IllegalActionException("not permitted")));

            assertEquals(OutputResponse.Builder.ILLEGAL_ACTION, response.get("statusCode").getAsInt());
            assertTrue(response.get("statusMessage").getAsString().startsWith("Illegal Action performed"));
        }

        @Test
        @DisplayName("setErrorMessage should map a JDBC failure to the environment code")
        void setErrorMessage_shouldMapJdbcFailure() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new JDBCException("pool exhausted")));

            assertEquals(OutputResponse.Builder.ENVIRONMENT_EXCEPTION, response.get("statusCode").getAsInt());
            assertTrue(response.get("statusMessage").getAsString().startsWith("Failed with DB connection issues at "));
        }

        @Test
        @DisplayName("setErrorMessage should map a SQL grammar failure to the code-exception code")
        void setErrorMessage_shouldMapSqlGrammarFailure() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new SQLGrammarException("bad column")));

            assertEquals(OutputResponse.Builder.CODE_EXCEPTION, response.get("statusCode").getAsInt());
        }

        @Test
        @DisplayName("setErrorMessage should map a constraint violation to the code-exception code")
        void setErrorMessage_shouldMapConstraintViolation() {
            JsonObject response = build(new OutputResponse.Builder()
                    .setErrorMessage(new ConstraintViolationException("duplicate key")));

            assertEquals(OutputResponse.Builder.CODE_EXCEPTION, response.get("statusCode").getAsInt());
        }
    }
}
