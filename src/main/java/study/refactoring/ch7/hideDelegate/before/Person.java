package study.refactoring.ch7.hideDelegate.before;

public class Person {
    private String name;
    private Department department;

    public Person(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
