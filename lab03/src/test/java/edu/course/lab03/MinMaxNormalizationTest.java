package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinMaxNormalizationTest {

    @Test
    void normalizesToZeroOne() {
        MinMaxNormalization strategy = new MinMaxNormalization();
        double[] result = strategy.normalize(new double[]{1.0, 2.0, 3.0});
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, result, 1e-9);
    }

    @Test
    void returnsZerosWhenAllValuesEqual() {
        MinMaxNormalization strategy = new MinMaxNormalization();
        double[] result = strategy.normalize(new double[]{5.0, 5.0, 5.0});
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result, 1e-9);
    }

    @Test
    void doesNotMutateInput() {
        MinMaxNormalization strategy = new MinMaxNormalization();
        double[] input = {1.0, 2.0, 3.0};
        double[] copy = input.clone();
        strategy.normalize(input);
        assertArrayEquals(copy, input, 1e-9);
    }

    @Test
    void handlesEmptyArray() {
        MinMaxNormalization strategy = new MinMaxNormalization();
        double[] result = strategy.normalize(new double[0]);
        assertEquals(0, result.length);
    }

    @Test
    void rejectsNull() {
        MinMaxNormalization strategy = new MinMaxNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }
}