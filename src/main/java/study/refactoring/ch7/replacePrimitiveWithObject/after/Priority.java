package study.refactoring.ch7.replacePrimitiveWithObject.after;

public class Priority {
    private String value;

    public Priority(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
