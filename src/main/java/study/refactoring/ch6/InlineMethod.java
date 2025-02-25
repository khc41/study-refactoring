package study.refactoring.ch6;

import java.util.ArrayList;
import java.util.List;

public class InlineMethod {

	public static int rating(Driver aDriver) {
		return aDriver.numberOfLateDeliveries > 5 ? 2 : 1;
	}

	public static List<List<String>> reportLines(Customer aCustomer) {
		final List<List<String>> lines = new ArrayList<>();
		lines.add(List.of("name", aCustomer.getName()));
		lines.add(List.of("location", aCustomer.getLocation()));
		return lines;
	}

	public static class Customer {
		private String name;
		private String location;

		public Customer(String name, String location) {
			this.name = name;
			this.location = location;
		}

		public String getName() {
			return name;
		}

		public String getLocation() {
			return location;
		}
	}

	public static class Driver {
		private int numberOfLateDeliveries;

		public Driver(int numberOfLateDeliveries) {
			this.numberOfLateDeliveries = numberOfLateDeliveries;
		}
	}
}
