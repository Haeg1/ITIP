package edu.course.lab02;

public record SampleId(String value) {
    public SampleId {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}