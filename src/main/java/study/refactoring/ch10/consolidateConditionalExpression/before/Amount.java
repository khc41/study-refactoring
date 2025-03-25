package study.refactoring.ch10.consolidateConditionalExpression.before;

public class Amount {

    public int disabilityAmount(Employee anEmployee) {
        if (anEmployee.seniority < 2) {
            return 0;
        }
        if (anEmployee.monthsDisabled < 12) {
            return 0;
        }
        if (anEmployee.isPartTime) {
            return 0;
        }
        //장애 수당 계산
        return 0;
    }

    public static class Employee {
        private int seniority;
        private int monthsDisabled;
        private boolean isPartTime;
    }
}
