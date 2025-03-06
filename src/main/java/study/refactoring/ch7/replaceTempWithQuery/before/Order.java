package study.refactoring.ch7.replaceTempWithQuery.before;

public class Order {
    private int quantity;
    private Item item;

    public Order(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    public double getPrice() {
        int basePrice = this.quantity * this.item.getPrice();
        double discountFactor = 0.98;

        if (basePrice > 1000) discountFactor -= 0.03;
        return basePrice * discountFactor;
    }
}
