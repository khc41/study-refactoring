package study.refactoring.ch8.splitLoop.after;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SalaryTest {

    @Test
    void testGetSalaryString() {
        Salary salary = new Salary();
        List<Salary.People> people = List.of(
                new Salary.People(24, 1000000),
                new Salary.People(36, 1230000)
        );

        assertThat(salary.getSalaryString(people)).isEqualTo("최연소: 24, 총 급여: 2230000");
    }
}