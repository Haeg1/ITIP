package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DataSampleTest {

    // Вспомогательный метод для создания валидного объекта
    private DataSample createSample() {
        return new DataSample(
            new SampleId("a1"),
            "dog",
            SampleStatus.RAW,
            new double[]{1.0, 2.0, 3.0}
        );
    }

    @Test
    void constructorCreatesValidSample() {
        DataSample s = createSample();
        assertEquals("a1", s.getId().value());
        assertEquals("dog", s.getLabel());
        assertEquals(SampleStatus.RAW, s.getStatus());
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, s.getFeatures(), 1e-9);
    }

    @Test
    void constructorRejectsNullOrBlankId() {
        // Проверка на null
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(null, "dog", SampleStatus.RAW, new double[]{1.0}));

        // Проверка на пустую строку внутри SampleId
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId(""), "dog", SampleStatus.RAW, new double[]{1.0}));

        // Проверка на пробелы внутри SampleId
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("   "), "dog", SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void constructorRejectsNullOrBlankLabel() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("a1"), null, SampleStatus.RAW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("a1"), "", SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void constructorRejectsNullStatus() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("a1"), "dog", null, new double[]{1.0}));
    }

    @Test
    void constructorRejectsNullOrEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("a1"), "dog", SampleStatus.RAW, null));
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(new SampleId("a1"), "dog", SampleStatus.RAW, new double[]{}));
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
        DataSample s = createSample(); // {1, 2, 3} -> среднее 2.0
        assertEquals(2.0, s.meanFeatures(), 1e-9);
    }

    @Test
    void featuresAreCopiedInConstructor() {
        double[] original = {1.0, 2.0, 3.0};
        DataSample s = new DataSample(new SampleId("a1"), "dog", SampleStatus.RAW, original);

        original[0] = 999.0; // меняем внешний массив
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, s.getFeatures(), 1e-9);
    }

    @Test
    void featuresAreCopiedOnGet() {
        DataSample s = createSample();
        double[] fromGetter = s.getFeatures();
        fromGetter[0] = 999.0; // меняем полученную копию
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, s.getFeatures(), 1e-9);
    }
}