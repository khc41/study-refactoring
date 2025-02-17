package study.refactoring.ch4.data;

import java.util.Map;

public class Producer {
    private Province province;
    private String name;
    private int cost;
    private int production;

    public Producer(Province aProvince, Map<String, Object> data) {
        this.province = aProvince;
        this.cost = (int) data.get("cost");
        this.name = (String) data.get("name");
        this.production = data.get("production") != null ? (int) data.get("production") : 0;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = Integer.parseInt(cost);
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(String amountString) {
        final double amount = Double.parseDouble(amountString);
        final int newProduction = Double.isNaN(amount) ? 0 : (int) amount;
        this.province.setTotalProduction(this.province.getTotalProduction() + newProduction - this.production);
        this.production = newProduction;
    }
}
