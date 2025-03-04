package study.refactoring.ch7.encapsulateCollection.after;

import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private List<Course> courses;

    public Person(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void setCourses(List<Course> courses) {
        this.courses = Collections.unmodifiableList(courses);
    }

    public void addCourse(Course aCourse) {
        this.courses.add(aCourse);
    }

    public void removeCourse(Course aCourse) {
        final int index = this.courses.indexOf(aCourse);
        if (index == -1) {
            throw new IllegalArgumentException();
        }
        this.courses.remove(aCourse);
    }
}
