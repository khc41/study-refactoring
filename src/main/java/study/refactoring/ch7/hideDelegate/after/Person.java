package study.refactoring.ch7.hideDelegate.after;

public class Person {
    private String name;
    private Department department;

    public Person(String name) {
        this.name = name;
    }

    // 위임 메서드 추가
    public String getManager(){
        return this.department.getManager();
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
