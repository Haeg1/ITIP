package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeatureProcessorTest {

    @Test
    void worksWithMinMax() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        double[] result = processor.process(new double[]{1.0, 2.0, 3.0});
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, result, 1e-9);
    }

    @Test
    void worksWithMeanCentering() {
        FeatureProcessor processor = new FeatureProcessor(new MeanCenteringNormalization());
        double[] result = processor.process(new double[]{1.0, 2.0, 3.0});
        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, result, 1e-9);
    }

    @Test
    void strategyCanBeReplacedWithoutChangingProcessor() {
        double[] input = {1.0, 2.0, 3.0};

        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor centered = new FeatureProcessor(new MeanCenteringNormalization());

        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, minMax.process(input), 1e-9);
        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, centered.process(input), 1e-9);
    }

    @Test
    void processorDoesNotMutateInput() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        double[] input = {1.0, 2.0, 3.0};
        double[] copy = input.clone();

        processor.process(input);

        assertArrayEquals(copy, input, 1e-9);
    }

    @Test
    void rejectsNull() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        assertThrows(IllegalArgumentException.class, () -> processor.process(null));
    }
}