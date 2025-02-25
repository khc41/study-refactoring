package study.refactoring.ch6;

public class ExtractVariable {

	public static double price(Order order) {
		final double basePrice = order.getQuantity() * order.getItemPrice();
		final double quantityDiscount = Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05;
		final double shipping = Math.min(basePrice * 0.1, 100);
		return basePrice - quantityDiscount + shipping;
	}

	public static class Order {
		private int quantity;
		private int itemPrice;

		public Order(int quantity, int itemPrice) {
			this.quantity = quantity;
			this.itemPrice = itemPrice;
		}

		public double price() {
			return getBasePrice() - getQuantityDiscount() + getShipping();
		}

		private double getBasePrice() {
			return this.quantity * this.itemPrice;
		}

		private double getQuantityDiscount() {
			return Math.max(0, this.quantity - 500) * this.itemPrice * 0.05;
		}

		private double getShipping() {
			return Math.min(this.quantity * this.itemPrice * 0.1, 100);
		}

		public int getQuantity() {
			return quantity;
		}

		public int getItemPrice() {
			return itemPrice;
		}
	}
}
