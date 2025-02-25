package study.refactoring.ch6;

import java.util.List;
import java.util.Queue;

public class ChangeFunctionDeclaration {

	public static double circumference(double radius) {
		return 2 * Math.PI * radius;
	}

	private static boolean inNewEngland(String stateCode) {
		return List.of("MA", "CT", "ME", "VT", "NH", "RI").contains(stateCode);
	}

	public static class Book {
		private Queue<Customer> reservations;

		public void addReservation(Customer customer, boolean isPriority) {
			reservations.add(customer);
		}

	}

	public static class Customer {
		private Address address;

		public Address getAddress() {
			return address;
		}
	}

	public static class Address {
		private String state;

		public String getState() {
			return state;
		}
	}
}
