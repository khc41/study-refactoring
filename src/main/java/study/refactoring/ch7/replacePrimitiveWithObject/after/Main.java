package study.refactoring.ch7.replacePrimitiveWithObject.after;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Order> orders = List.of(new Order("high"), new Order("rush"));
        int highPriorityCount = (int) orders.stream().filter(o -> "high".equals(o.getPriorityString())
                        || "rush".equals(o.getPriorityString()))
                .count();
    }
}
