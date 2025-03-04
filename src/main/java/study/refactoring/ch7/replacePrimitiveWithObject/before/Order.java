package study.refactoring.ch7.replacePrimitiveWithObject.before;

public class Order {
    private String priority;

    public Order(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
