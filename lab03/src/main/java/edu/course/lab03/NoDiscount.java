package edu.course.lab03;

public class NoDiscount implements DiscountPolicy {

    @Override 
    public double apply(double price) {
        if (price < 0 ) {
            throw new IllegalArgumentException("Price must be more than 0");
        }
        return price;
    }
}