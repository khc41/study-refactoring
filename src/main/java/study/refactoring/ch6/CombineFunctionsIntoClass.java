package study.refactoring.ch6;

import java.util.HashMap;
import java.util.Map;

public class CombineFunctionsIntoClass {
	public static class Client1 {

		private final Map<String, Object> rawReading = acquireReading();
		private final Reading aReading = new Reading(rawReading);
		private final double baseCharge = aReading.baseCharge();
	}

	public static class Client2 {
		private final Map<String, Object> rawReading = acquireReading();
		private final Reading aReading = new Reading(rawReading);
		private final double taxableCharge = aReading.taxableCharge(aReading);

	}

	public static class Client3 {
		private final Map<String, Object> rawReading = acquireReading();
		private final Reading aReading = new Reading(rawReading);
		private final double baseChargeAmount = aReading.baseCharge();
	}

	public static class Reading {
		private String customer;
		private int quantity;
		private int month;
		private int year;

		public Reading(Map<String, Object> rawReading) {
			this.customer = (String)rawReading.get("customer");
			this.quantity = (int)rawReading.get("quantity");
			this.month = (int)rawReading.get("month");
			this.year = (int)rawReading.get("year");
		}

		public Reading(String customer, int quantity, int month, int year) {
			this.customer = customer;
			this.quantity = quantity;
			this.month = month;
			this.year = year;
		}

		public double baseCharge() {
			return baseRate(month, year) * quantity;
		}

		public double taxableCharge(Reading aReading) {
			return Math.max(0, aReading.baseCharge() - taxThreshold(aReading.getYear()));
		}

		public String getCustomer() {
			return customer;
		}

		public int getQuantity() {
			return quantity;
		}

		public int getMonth() {
			return month;
		}

		public int getYear() {
			return year;
		}
	}

	public static double taxThreshold(int year) {
		return 0;
	}

	private static double baseRate(int month, int year) {
		// some calculate logic
		return 0;
	}

	private static Map<String, Object> acquireReading() {
		Map<String, Object> map = new HashMap<>();
		map.put("customer", "ivan");
		map.put("quantity", 10);
		map.put("month", 5);
		map.put("year", 2017);
		return map;
	}

}
