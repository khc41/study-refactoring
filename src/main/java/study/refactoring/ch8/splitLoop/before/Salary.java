package study.refactoring.ch8.splitLoop.before;

import java.util.List;

public class Salary {

    public String getSalaryString(List<People> people) {
        int youngest = !people.isEmpty() ? people.getFirst().getAge() : Integer.MAX_VALUE;
        int totalSalary = 0;
        for (People p : people) {
            if (p.getAge() < youngest) {
                youngest = p.getAge();
            }
            totalSalary += p.getSalary();
        }
        return String.format("최연소: %d, 총 급여: %s", youngest, totalSalary);
    }

    public static class People {
        private int age;
        private int salary;

        public int getAge() {
            return age;
        }

        public int getSalary() {
            return salary;
        }
    }
}
