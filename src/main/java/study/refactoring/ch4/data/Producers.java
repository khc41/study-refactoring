package study.refactoring.ch4.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Producers {
	private final List<Producer> producers = new ArrayList<>();
	private int totalProduction;

	public Producers() {
	}

	public Producers(List<Map<String, Object>> producerList) {
		producerList.forEach(input -> {
			Producer newProducer = new Producer(input);
			this.producers.add(newProducer);
			this.totalProduction += newProducer.getProduction();
		});
	}

	public static Producers of(List<Producer> producers) {
		Producers newProducer = new Producers();
		producers.forEach(producer -> {
			newProducer.producers.add(producer);
			newProducer.totalProduction += producer.getProduction();
		});
		return newProducer;
	}

	public int getTotalProduction() {
		return totalProduction;
	}

	public List<Producer> getProducers() {
		return Collections.unmodifiableList(producers);
	}

	public int getDemandCost(int demand) {
		int remainingDemand = demand;
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

	public void addProducer(Producer producer) {
		this.producers.add(producer);
		this.totalProduction += producer.getProduction();
	}

	public void updateProduction(String producerName, String production) {
		Producer producer = this.producers.stream()
			.filter(p -> p.getName().equals(producerName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Producer not found"));
		updateTotalProduction(production, producer);
	}

	public void updateProduction(int index, String production) {
		if (index < 0 || index >= producers.size()) {
			throw new IllegalArgumentException("Invalid producer index");
		}
		updateTotalProduction(production, producers.get(index));
	}

	private void updateTotalProduction(String production, Producer producer) {
		this.totalProduction -= producer.getProduction();
		producer.setProduction(production);
		this.totalProduction += producer.getProduction();
	}
}
