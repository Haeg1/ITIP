package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeanCenteringNormalizationTest {

    @Test
    void centersAroundMean() {
        MeanCenteringNormalization strategy = new MeanCenteringNormalization();
        double[] result = strategy.normalize(new double[]{1.0, 2.0, 3.0});
        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, result, 1e-9);
    }

    @Test
    void singleValueBecomesZero() {
        MeanCenteringNormalization strategy = new MeanCenteringNormalization();
        double[] result = strategy.normalize(new double[]{5.0});
        assertArrayEquals(new double[]{0.0}, result, 1e-9);
    }

    @Test
    void allEqualValuesBecomeZero() {
        MeanCenteringNormalization strategy = new MeanCenteringNormalization();
        double[] result = strategy.normalize(new double[]{7.0, 7.0, 7.0});
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result, 1e-9);
    }

    @Test
    void doesNotMutateInput() {
        MeanCenteringNormalization strategy = new MeanCenteringNormalization();
        double[] input = {1.0, 2.0, 3.0};
        double[] copy = input.clone();
        strategy.normalize(input);
        assertArrayEquals(copy, input, 1e-9);
    }

    @Test
    void handlesEmptyArray() {
        MeanCenteringNormalization strategy = new MeanCenteringNormalization();
        double[] result = strategy.normalize(new double[0]);
        assertEquals(0, result.length);
    }

    @Test
    void rejectsNull() {
        MeanCenteringNormalization strategy = new MeanCenteringNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }
}