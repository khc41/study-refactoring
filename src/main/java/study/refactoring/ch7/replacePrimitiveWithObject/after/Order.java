package study.refactoring.ch7.replacePrimitiveWithObject.after;

public class Order {
    private Priority priority;

    public Order(String priority) {
        this(new Priority(priority));
    }

    public Order(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getPriorityString() {
        return priority.toString();
    }

    public void setPriority(String priority) {
        this.priority = new Priority(priority);
    }
}
