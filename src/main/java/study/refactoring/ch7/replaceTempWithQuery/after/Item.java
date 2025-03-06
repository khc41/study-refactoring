package study.refactoring.ch7.replaceTempWithQuery.after;

public class Item {
    private int price;

    public Item(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
