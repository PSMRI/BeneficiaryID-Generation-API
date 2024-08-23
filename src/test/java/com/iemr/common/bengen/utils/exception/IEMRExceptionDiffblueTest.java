package com.iemr.common.bengen.utils.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IEMRExceptionDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link IEMRException#IEMRException(String)}
     *   <li>{@link IEMRException#getMessage()}
     *   <li>{@link IEMRException#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        IEMRException actualIemrException = new IEMRException("An error occurred");
        String actualMessage = actualIemrException.getMessage();

        // Assert
        assertEquals("An error occurred", actualMessage);
        assertEquals("An error occurred", actualIemrException.toString());
    }

    /**
     * Method under test: {@link IEMRException#IEMRException(String, Throwable)}
     */
    @Test
    void testNewIEMRException() {
        // Arrange, Act and Assert
        assertEquals("An error occurred", (new IEMRException("An error occurred", new Throwable())).getLocalizedMessage());
    }
}
