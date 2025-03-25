package study.refactoring.ch10.consolidateConditionalExpression.after;

public class Amount {

    public int disabilityAmount(Employee anEmployee) {
        if (isNotEligibleForDisability(anEmployee)) {
            return 0;
        }
        //장애 수당 계산
        return 0;
    }

    private static boolean isNotEligibleForDisability(Employee anEmployee) {
        return anEmployee.seniority < 2
                || anEmployee.monthsDisabled < 12
                || anEmployee.isPartTime;
    }

    public static class Employee {
        private int seniority;
        private int monthsDisabled;
        private boolean isPartTime;
    }
}
