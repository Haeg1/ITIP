package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyTest {

    @Test
    void noDiscountReturnsSamePrice() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0), 1e-9);
    }

    @Test
    void noDiscountReturnsZeroForZeroPrice() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(0.0, calculator.calculate(0.0), 1e-9);
    }

    @Test
    void percentDiscount20On100Gives80() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, calculator.calculate(100.0), 1e-9);
    }

    @Test
    void percentDiscount0DoesNotChangePrice() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(0));
        assertEquals(100.0, calculator.calculate(100.0), 1e-9);
    }

    @Test
    void percentDiscount100MakesPriceZero() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(100));
        assertEquals(0.0, calculator.calculate(100.0), 1e-9);
    }

    @Test
    void negativePriceIsRejected() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-1.0));
    }

    @Test
    void percentBelowZeroIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-1));
    }

    @Test
    void percentAbove100IsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101));
    }

    @Test
    void policyCanBeReplacedWithoutChangingCalculator() {
        double price = 100.0;
        PriceCalculator noDiscount = new PriceCalculator(new NoDiscount());
        PriceCalculator withDiscount = new PriceCalculator(new PercentDiscount(20));

        assertEquals(100.0, noDiscount.calculate(price), 1e-9);
        assertEquals(80.0, withDiscount.calculate(price), 1e-9);
    }
}