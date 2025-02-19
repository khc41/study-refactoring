package study.refactoring.ch4.data;

import java.util.Map;

public class Producer {
    private String name;
    private int cost;
    private int production;

    public Producer(Map<String, Object> data) {
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
		this.production = Double.isNaN(amount) ? 0 : (int) amount;
    }
}
