package study.refactoring.ch8.moveField.after;

import java.time.LocalDateTime;

public class CustomerContract {
    private LocalDateTime startDate;
    private double discountRate;

    public CustomerContract(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public CustomerContract(LocalDateTime startDate, double discountRate) {
        this.startDate = startDate;
        this.discountRate = discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}