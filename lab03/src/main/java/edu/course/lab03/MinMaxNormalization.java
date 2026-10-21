package edu.course.lab03;

public class MinMaxNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Values must not be null");
        }
        if (values.length == 0) {
            return new double[0];
        }

        double min = values[0];
        double max = values[0];

        for (double value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        double[] result = new double[values.length];

        if (max == min) {
            return result;
        }

        double range = max - min;
        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - min) / range;
        }
        return result;
    }
}