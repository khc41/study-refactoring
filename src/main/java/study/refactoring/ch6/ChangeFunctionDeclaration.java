package study.refactoring.ch6;

import java.util.Queue;

public class ChangeFunctionDeclaration {

	public static double circumference(double radius) {
		return 2 * Math.PI * radius;
	}

	public static class Book {
		private Queue<Customer> reservations;

		public void addReservation(Customer customer, boolean isPriority) {
			reservations.add(customer);
		}

	}

	public static class Customer {

	}
}
