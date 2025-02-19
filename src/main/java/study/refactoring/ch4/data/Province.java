package study.refactoring.ch4.data;

import java.util.*;

public class Province {
    private String name;
    private List<Producer> producers;
    private Producers producers1;
    private int demand;
    private int price;

    public Province(Map<String, Object> doc) {
        this.name = (String) doc.get("name");

        this.producers = new ArrayList<>();
        this.demand = (int) doc.get("demand");
        this.price = (int) doc.get("price");

        List<Map<String, Object>> producersList = (List<Map<String, Object>>) doc.get("producers");
        producersList.forEach(d -> this.addProducer(new Producer(this, d)));

		this.producers1 = new Producers((List<Map<String, Object>>) doc.get("producers"));
    }

    private void addProducer(Producer arg) {
        this.producers.add(arg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Producer> getProducers() {
        return Collections.unmodifiableList(producers);
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public int getTotalProduction() {
        return producers.stream().map(Producer::getProduction).reduce(0, Integer::sum);
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = Integer.parseInt(demand);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }

    public int getShortfall() {
        return this.demand - getTotalProduction();
    }

    public int getProfit() {
        return this.getDemandValue() - this.getDemandCost();
    }

    private int getDemandValue() {
        return this.getSatisfiedDemand() * this.price;
    }

    private int getSatisfiedDemand() {
        return Math.min(this.demand, getTotalProduction());
    }

    private int getDemandCost() {
        int remainingDemand = this.demand;
        int result = 0;

        List<Producer> sortedProducers = this.producers.stream()
                .sorted(Comparator.comparingInt(Producer::getCost))
                .toList();

        for (Producer p : sortedProducers) {
            int contribution = Math.min(remainingDemand, p.getProduction());
            remainingDemand -= contribution;
            result += contribution * p.getCost();
        }

        return result;
    }
}
