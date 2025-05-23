package study.refactoring.ch1.data;

import java.util.Collections;
import java.util.List;

public class Invoice {
    private String customer;
    private List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return Collections.unmodifiableList(performances);
    }
}
