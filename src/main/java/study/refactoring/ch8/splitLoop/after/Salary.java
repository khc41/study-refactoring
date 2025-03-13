package study.refactoring.ch8.splitLoop.after;

import java.util.List;

public class Salary {

    public String getSalaryString(List<People> people) {

        int totalSalary = totalSalary(people);
        int youngest = youngestAge(people);
        return String.format("최연소: %d, 총 급여: %s", youngest, totalSalary);
    }

    private static int totalSalary(List<People> people) {
        return people.stream()
                .mapToInt(People::salary)
                .sum();
    }

    private static int youngestAge(List<People> people) {
        return people.stream()
                .mapToInt(People::age)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    public record People(int age, int salary) {
    }
}
