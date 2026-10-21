package edu.course.lab03;

public class PercentDiscount implements DiscountPolicy{
    private final double percent;

    public PercentDiscount(double percent){
        if (percent < 0 || percent > 100){
            throw new IllegalArgumentException("Процент должен быть в диапазоне от 0 до 100");
        }
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        return price * (1.0 - percent / 100);
    } 
}