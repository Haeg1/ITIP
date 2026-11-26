package edu.course.lab03;

public class ZScoreNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Values must not be null");
        }
        if (values.length == 0) {
            return new double[0];
        }

        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        double mean = sum / values.length;

        double variance = 0.0;
        for (double value : values) {
            variance += (value - mean) * (value - mean);
        }
        variance /= values.length;

        double std = Math.sqrt(variance);
        double[] result = new double[values.length];

        if (std == 0.0) {
            return result;
        }

        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - mean) / std;
        }
        return result;
    }
}
