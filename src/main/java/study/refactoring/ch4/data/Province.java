package study.refactoring.ch4.data;

import java.util.List;
import java.util.Map;

public class Province {
	private String name;
	private Producers producers;
	private int demand;
	private int price;

	public Province(Map<String, Object> doc) {
		this.name = (String)doc.get("name");
		this.demand = (int)doc.get("demand");
		this.price = (int)doc.get("price");
		this.producers = new Producers((List<Map<String, Object>>)doc.get("producers"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Producers getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = Producers.of(producers);
	}

	public int getTotalProduction() {
		return producers.getTotalProduction();
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
		return this.getDemandValue() - producers.getDemandCost(this.demand);
	}

	private int getDemandValue() {
		return this.getSatisfiedDemand() * this.price;
	}

	private int getSatisfiedDemand() {
		return Math.min(this.demand, getTotalProduction());
	}

}
