package study.refactoring.ch8.moveField.before;

import java.time.LocalDateTime;

public class Customer {
    private String name;
    private double discountRate;
    private CustomerContract contract;

    public Customer(String name, double discountRate) {
        this.name = name;
        this.discountRate = discountRate;
        this.contract = new CustomerContract(LocalDateTime.now());
    }

        public void becomePreferred() {
        discountRate += 0.03;
        // 다른 멋진 일들
    }

    public double applyDiscount(int amount) {
        return amount - (amount * this.discountRate);
    }

    public double getDiscountRate() {
        return discountRate;
    }
}
