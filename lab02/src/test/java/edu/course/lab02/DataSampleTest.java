package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DataSampleTest {

    private DataSample createSample() {
        return new DataSample(
            new SampleId("sample-42"), 
            "iris", 
            SampleStatus.RAW, 
            new double[]{2.0, 4.0, 6.0}
        );
    }

    @Test
    void constructorCreatesValidSample() {
        DataSample s = createSample();
        assertEquals("sample-42", s.getId().value());
        assertEquals("iris", s.getLabel());
        assertEquals(SampleStatus.RAW, s.getStatus());
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, s.getFeatures(), 1e-9);
    }

    @Test
    void constructorRejectsNullId() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(null, "iris", SampleStatus.RAW, new double[]{5.0}));
    }

    @Test
    void constructorRejectsEmptyIdValue() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId(""), "iris", SampleStatus.RAW, new double[]{5.0}));
    }

    @Test
    void constructorRejectsNullOrBlankLabel() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("ok"), null, SampleStatus.RAW, new double[]{5.0}));
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("ok"), "", SampleStatus.RAW, new double[]{5.0}));
    }

    @Test
    void constructorRejectsNullStatus() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("ok"), "iris", null, new double[]{5.0}));
    }

    @Test
    void constructorRejectsNullOrEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("ok"), "iris", SampleStatus.RAW, null));
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("ok"), "iris", SampleStatus.RAW, new double[]{}));
    }

    @Test
    void changeStatusUpdatesStatus() {
        DataSample s = createSample();
        s.changeStatus(SampleStatus.PROCESSING);
        assertEquals(SampleStatus.PROCESSING, s.getStatus());
    }

    @Test
    void changeStatusRejectsNull() {
        DataSample s = createSample();
        assertThrows(IllegalArgumentException.class, () -> s.changeStatus(null));
    }

    @Test
    void isReadyOnlyTrueForReadyStatus() {
        DataSample s = createSample();
        assertFalse(s.isReady());

        s.changeStatus(SampleStatus.READY);
        assertTrue(s.isReady());

        s.changeStatus(SampleStatus.REJECTED);
        assertFalse(s.isReady());
    }

    @Test
    void meanFeaturesCalculated() {
        DataSample s = createSample(); 
        assertEquals(4.0, s.meanFeatures(), 1e-9);
    }

    @Test
    void featuresAreCopiedInConstructor() {
        double[] original = {3.0, 6.0, 9.0};
        DataSample s = new DataSample(new SampleId("ok"), "iris", SampleStatus.RAW, original);

        original[0] = 100.0; 
        assertArrayEquals(new double[]{3.0, 6.0, 9.0}, s.getFeatures(), 1e-9);
    }

    @Test
    void featuresAreCopiedOnGet() {
        DataSample s = createSample();
        double[] fromGetter = s.getFeatures();
        fromGetter[0] = 100.0; 
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, s.getFeatures(), 1e-9);
    }
}