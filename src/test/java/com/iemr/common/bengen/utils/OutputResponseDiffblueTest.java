package com.iemr.common.bengen.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class OutputResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OutputResponse.Builder#build()}
     *   <li>{@link OutputResponse.Builder#setData(String)}
     *   <li>{@link OutputResponse.Builder#setDataJsonType(String)}
     *   <li>{@link OutputResponse.Builder#setDataObjectType(String)}
     *   <li>{@link OutputResponse.Builder#setMethodName(String)}
     *   <li>{@link OutputResponse.Builder#setStatusCode(Integer)}
     *   <li>{@link OutputResponse.Builder#setStatusMessage(String)}
     *   <li>{@link OutputResponse.Builder#setStatusMessageLong(String)}
     * </ul>
     */
    @Test
    void testBuilderBuild() throws JsonParseException {
        // Arrange, Act and Assert
        JsonElement response = (new OutputResponse.Builder()).setData("Str")
                .setDataJsonType("Str")
                .setDataObjectType("Str")
                .setMethodName("Str")
                .setStatusCode(1)
                .setStatusMessage("Str")
                .setStatusMessageLong("Str")
                .build()
                .getResponse();
        assertTrue(response instanceof JsonObject);
        assertEquals(7, ((JsonObject) response).size());
        assertFalse(response.isJsonArray());
        assertFalse(response.isJsonNull());
        assertFalse(response.isJsonPrimitive());
        assertFalse(((JsonObject) response).isEmpty());
        assertTrue(response.isJsonObject());
        assertSame(response, response.getAsJsonObject());
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link OutputResponse.Builder}
     */
    @Test
    void testBuilderNewBuilder() {
        // Arrange, Act and Assert
        JsonElement response = (new OutputResponse.Builder()).build().getResponse();
        assertEquals(7, ((JsonObject) response).size());
        assertFalse(response.isJsonArray());
        assertFalse(response.isJsonNull());
        assertFalse(response.isJsonPrimitive());
        assertFalse(((JsonObject) response).isEmpty());
        assertTrue(response.isJsonObject());
        assertSame(response, response.getAsJsonObject());
    }

    /**
     * Method under test: {@link OutputResponse.Builder#setErrorMessage(Throwable)}
     */
    @Test
    void testBuilderSetErrorMessage() {
        // Arrange
        OutputResponse.Builder builder = new OutputResponse.Builder();

        // Act and Assert
        assertSame(builder, builder.setErrorMessage(new Throwable()));
    }

    /**
     * Method under test: {@link OutputResponse.Builder#setErrorMessage(Throwable)}
     */
    @Test
    void testBuilderSetErrorMessage2() {
        // Arrange
        OutputResponse.Builder builder = new OutputResponse.Builder();

        // Act and Assert
        assertSame(builder, builder.setErrorMessage(new IOException("Failed with generic exception")));
    }

    /**
     * Method under test: {@link OutputResponse.Builder#setErrorMessage(Throwable)}
     */
    @Test
    void testBuilderSetErrorMessage3() {
        // Arrange
        OutputResponse.Builder builder = new OutputResponse.Builder();

        // Act and Assert
        assertSame(builder, builder.setErrorMessage(new ArrayIndexOutOfBoundsException(-1)));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link OutputResponse}
     *   <li>{@link OutputResponse#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals("{}", (new OutputResponse()).toString());
    }
}
