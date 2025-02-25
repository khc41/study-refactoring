package study.refactoring.ch6;

public class ExtractVariable {

	public static double price(Order order) {
		return order.getQuantity() * order.getItemPrice() -
			Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05 +
			Math.min(order.getQuantity() * order.getItemPrice() * 0.1, 100);
	}

	public static class Order {
		private int quantity;
		private int itemPrice;

		public Order(int quantity, int itemPrice) {
			this.quantity = quantity;
			this.itemPrice = itemPrice;
		}

		public int getQuantity() {
			return quantity;
		}

		public int getItemPrice() {
			return itemPrice;
		}
	}
}
