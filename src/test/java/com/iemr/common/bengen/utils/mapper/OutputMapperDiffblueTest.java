package com.iemr.common.bengen.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

class OutputMapperDiffblueTest {
    /**
     * Method under test: {@link OutputMapper#getInstance()}
     */
    @Test
    void testGetInstance() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        OutputMapper.getInstance();
    }

    /**
     * Method under test: {@link OutputMapper#gson()}
     */
    @Test
    void testGson() {
        // Arrange and Act
        Gson actualGsonResult = OutputMapper.gson();

        // Assert
        assertTrue(actualGsonResult.htmlSafe());
        assertTrue(actualGsonResult.serializeNulls());
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link OutputMapper}
     */
    @Test
    void testNewOutputMapper() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        new OutputMapper();
    }
}
