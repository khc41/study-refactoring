package study.refactoring.ch8.moveField.after;

import java.time.LocalDateTime;

public class Customer {
    private String name;
    private CustomerContract contract;

    public Customer(String name, double discountRate) {
        this.name = name;
        this.contract = new CustomerContract(LocalDateTime.now());
        setDiscountRate(discountRate);
    }

    public void becomePreferred() {
        setDiscountRate(getDiscountRate() + 0.03);
        // 다른 멋진 일들
    }

    public double applyDiscount(int amount) {
        return amount - (amount * this.getDiscountRate());
    }

    public void setDiscountRate(double aNumber) {
        contract.setDiscountRate(aNumber);
    }

    public double getDiscountRate() {
        return contract.getDiscountRate();
    }
}
