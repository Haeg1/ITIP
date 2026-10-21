package edu.course.lab03;

public class FeatureProcessor {

    private final NormalizationStrategy normalizationStrategy;

    public FeatureProcessor(NormalizationStrategy normalizationStrategy) {
        this.normalizationStrategy = normalizationStrategy;
    }

    public double[] process(double[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Values must not be null");
        }
        double[] safeInput = values.clone(); 
        return normalizationStrategy.normalize(safeInput);
    }
}