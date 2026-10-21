package edu.course.lab03;

public class PriceCalculator {

    private final DiscountPolicy discountPolicy;

    public PriceCalculator(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public double calculate(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        return discountPolicy.apply(price);
    }
}
