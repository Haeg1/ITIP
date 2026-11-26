package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZScoreNormalizationTest {

    @Test
    void producesZeroMeanAndUnitStd() {
        ZScoreNormalization strategy = new ZScoreNormalization();
        double[] result = strategy.normalize(new double[]{1.0, 2.0, 3.0});

        double sum = 0.0;
        for (double v : result) sum += v;
        assertEquals(0.0, sum / result.length, 1e-9);

        double variance = 0.0;
        for (double v : result) variance += v * v;
        variance /= result.length;
        assertEquals(1.0, Math.sqrt(variance), 1e-9);
    }

    @Test
    void allEqualValuesBecomeZero() {
        ZScoreNormalization strategy = new ZScoreNormalization();
        double[] result = strategy.normalize(new double[]{7.0, 7.0, 7.0});
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result, 1e-9);
    }

    @Test
    void doesNotMutateInput() {
        ZScoreNormalization strategy = new ZScoreNormalization();
        double[] input = {1.0, 2.0, 3.0};
        double[] copy = input.clone();
        strategy.normalize(input);
        assertArrayEquals(copy, input, 1e-9);
    }

    @Test
    void handlesEmptyArray() {
        ZScoreNormalization strategy = new ZScoreNormalization();
        double[] result = strategy.normalize(new double[0]);
        assertEquals(0, result.length);
    }

    @Test
    void rejectsNull() {
        ZScoreNormalization strategy = new ZScoreNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }

    @Test
    void worksWithZScoreViaProcessor() {
        FeatureProcessor processor = new FeatureProcessor(new ZScoreNormalization());
        double[] result = processor.process(new double[]{1.0, 2.0, 3.0});
        
        double sum = 0.0;
        for (double v : result) sum += v;
        assertEquals(0.0, sum / result.length, 1e-9);
    }
}