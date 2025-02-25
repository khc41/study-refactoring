package study.refactoring.ch6;

import java.util.ArrayList;
import java.util.List;

public class InlineMethod {

	public static int rating(Driver aDriver) {
		return aDriver.numberOfLateDeliveries > 5 ? 2 : 1;
	}

	public static List<List<String>> reportLines(Customer aCustomer) {
		final List<List<String>> lines = new ArrayList<>();
		gatherCustomerData(lines, aCustomer);
		return lines;
	}

	private static void gatherCustomerData(List<List<String>> out, Customer aCustomer) {
		out.add(List.of("name", aCustomer.getName()));
		out.add(List.of("location", aCustomer.getLocation()));
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
