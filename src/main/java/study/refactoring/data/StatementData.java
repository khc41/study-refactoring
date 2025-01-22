package study.refactoring.data;

import study.refactoring.data.PerformanceData;

import java.util.List;

public class StatementData {
    private String customer;
    private List<PerformanceData> performances;
    private int totalAmount;
    private int totalVolumeCredits;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalVolumeCredits() {
        return totalVolumeCredits;
    }

    public void setTotalVolumeCredits(int totalVolumeCredits) {
        this.totalVolumeCredits = totalVolumeCredits;
    }

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
