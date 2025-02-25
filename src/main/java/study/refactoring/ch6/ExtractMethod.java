package study.refactoring.ch6;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class ExtractMethod {

	public static void printOwing(Invoice invoice) {
		int outstanding = 0;

		printBanner();

		// 미해결 채무(outstanding)를 계산한다.
		for (Order o : invoice.getOrders()) {
			outstanding += o.getAmount();
		}

		// 마감일(dueDate)을 기록한다.
		LocalDateTime today = LocalDateTime.now();
		invoice.setDueDate(today.plusDays(30));

		printDetails(invoice, outstanding);
	}

	private static void printDetails(Invoice invoice, int outstanding) {
		// 세부 사항을 출력한다.
		System.out.printf("고객명: %s%n", invoice.getCustomer());
		System.out.printf("채무액: %s%n", outstanding);
		System.out.printf("마감일: %s%n", invoice.getDueDate().toLocalDate().toString());
	}

	private static void printBanner() {
		System.out.println("****************");
		System.out.println("**** 고객 채무 ****");
		System.out.println("****************");
	}

	public static class Invoice {
		private String customer;
		private LocalDateTime dueDate;
		private List<Order> orders;

		public Invoice(String customer, List<Order> orders) {
			this.customer = customer;
			this.orders = orders;
		}

		public String getCustomer() {
			return customer;
		}

		public LocalDateTime getDueDate() {
			return dueDate;
		}

		public void setDueDate(LocalDateTime dueDate) {
			this.dueDate = dueDate;
		}

		public List<Order> getOrders() {
			return orders;
		}
	}

	public static class Order {
		private int amount;

		public int getAmount() {
			return amount;
		}

		public Order(int amount) {
			this.amount = amount;
		}
	}
}
