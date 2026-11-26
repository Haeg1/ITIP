package edu.course.lab02;

import java.util.Arrays;

public class DataSample {
    // ЗАМЕНЕНО String на SampleId
    private final SampleId id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    // Конструктор теперь принимает SampleId
    public DataSample(SampleId id, String label, SampleStatus status, double[] features) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Label не может быть пустым");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status не может быть null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("Features не могут быть пустыми");
        }

        this.id = id;
        this.label = label;
        this.status = status;
        this.features = Arrays.copyOf(features, features.length);
    }

    public SampleId getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return Arrays.copyOf(features, features.length);
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Новый статус не может быть null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double meanFeatures() {
        double sum = 0.0;
        for (double feature : features) {
            sum += feature;
        }
        return sum / features.length;
    }

    // Дополнительная часть: нормализация
    public DataSample normalized(double min, double max) {
        if (!Double.isFinite(min) || !Double.isFinite(max) || max <= min) {
            throw new IllegalArgumentException("Некорректные границы нормализации");
        }

        double[] normalizedFeatures = new double[features.length];
        for (int i = 0; i < features.length; i++) {
            normalizedFeatures[i] = (features[i] - min) / (max - min);
        }

        // Создаем новый объект с тем же ID и лейблом, но новыми фичами
        return new DataSample(this.id, this.label, this.status, normalizedFeatures);
    }
}