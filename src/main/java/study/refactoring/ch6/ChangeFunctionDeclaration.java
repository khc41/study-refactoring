package study.refactoring.ch6;

import java.util.Queue;

public class ChangeFunctionDeclaration {

	private static double circum(double radius) {
		return circumference(radius);
	}

	private static double circumference(double radius) {
		return 2 * Math.PI * radius;
	}

	public static class Book {
		private Queue<Customer> reservations;

		public void addReservation(Customer customer) {
			reservations.add(customer);
		}

	}

	public static class Customer {

	}
}
