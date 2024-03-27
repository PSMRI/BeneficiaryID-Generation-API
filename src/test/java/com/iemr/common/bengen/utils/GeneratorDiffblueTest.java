package com.iemr.common.bengen.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GeneratorDiffblueTest {
    /**
     * Method under test: {@link Generator#generateBeneficiaryId()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateBeneficiaryId() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See Working with code R031 (https://diff.blue/R031) for details.

        // Arrange and Act
        (new Generator()).generateBeneficiaryId();
    }

    /**
     * Method under test: {@link Generator#generateFirst()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateFirst() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See Working with code R031 (https://diff.blue/R031) for details.

        // Arrange and Act
        (new Generator()).generateFirst();
    }

    /**
     * Method under test: {@link Generator#generateNumN(int)}
     */
    @Test
    void testGenerateNumN() {
        // Arrange, Act and Assert
        assertThrows(NegativeArraySizeException.class, () -> (new Generator()).generateNumN(-1));
    }

    /**
     * Method under test: {@link Generator#getDigitCount(BigInteger)}
     */
    @Test
    void testGetDigitCount() {
        // Arrange
        Generator generator = new Generator();

        // Act and Assert
        assertEquals(1, generator.getDigitCount(BigInteger.valueOf(1L)));
    }

    /**
     * Method under test: {@link Generator#getDigitCount(BigInteger)}
     */
    @Test
    void testGetDigitCount2() {
        // Arrange
        Generator generator = new Generator();

        // Act and Assert
        assertEquals(0, generator.getDigitCount(BigInteger.valueOf(0L)));
    }

    /**
     * Method under test: {@link Generator#getRandomNum()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRandomNum() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        Generator generator = new Generator();

        // Act
        generator.getRandomNum();

        // Assert
        BigInteger generateBeneficiaryIdResult = generator.generateBeneficiaryId();
        int actualLowestSetBit = generateBeneficiaryIdResult.getLowestSetBit();
        BigInteger generateFirstResult = generator.generateFirst();
        assertEquals("30000000000", generateFirstResult.toString());
        assertEquals("987631497274", generateBeneficiaryIdResult.toString());
        assertEquals(1, actualLowestSetBit);
        assertEquals(1, generateBeneficiaryIdResult.signum());
        assertEquals(1, generateFirstResult.signum());
        assertEquals(10, generateFirstResult.getLowestSetBit());
        assertArrayEquals(new byte[]{6, -4, '#', -84, 0}, generateFirstResult.toByteArray());
        assertArrayEquals(new byte[]{0, -27, -13, 'l', -80, ':'}, generateBeneficiaryIdResult.toByteArray());
    }

    /**
     * Method under test: {@link Generator#getRandomNumRad(int)}
     */
    @Test
    void testGetRandomNumRad() {
        // Arrange, Act and Assert
        assertEquals(0, (new Generator()).getRandomNumRad(1));
        assertThrows(ArithmeticException.class, () -> (new Generator()).getRandomNumRad(0));
    }

    /**
     * Method under test: {@link Generator#getRandomNumRadRange(int, int)}
     */
    @Test
    void testGetRandomNumRadRange() {
        // Arrange, Act and Assert
        assertEquals(3, (new Generator()).getRandomNumRadRange(3, 3));
    }

    /**
     * Method under test: {@link Generator#displayArrays(int[], int[])}
     */
    @Test
    void testDisplayArrays() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        (new Generator()).displayArrays(new int[]{1, 0, 1, 0}, new int[]{1, 0, 1, 0});
    }
}
