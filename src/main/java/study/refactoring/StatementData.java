package study.refactoring;

import java.util.List;

public class StatementData {
    private String customer;
    private List<PerformanceData> performances;

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    public void setPerformances(List<PerformanceData> performances) {
        this.performances = performances;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
