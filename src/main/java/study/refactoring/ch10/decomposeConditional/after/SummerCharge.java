package study.refactoring.ch10.decomposeConditional.after;

import java.time.LocalDateTime;

public class SummerCharge {
    private Plan plan;
    private double charge;
    private int quantity;

    public double calculateCharge(LocalDateTime aDate) {
        return summer(aDate) ? summerCharge() : regularCharge();
    }

    private double regularCharge() {
        return quantity * plan.regularRate + plan.regularServiceCharge;
    }

    private double summerCharge() {
        return quantity * plan.summerRate;
    }

    private boolean summer(LocalDateTime aDate) {
        return !aDate.isBefore(plan.summerStart) && !aDate.isAfter(plan.summerEnd);
    }

    public static class Plan {
        private LocalDateTime summerStart;
        private LocalDateTime summerEnd;
        private double summerRate;
        private double regularRate;
        private double regularServiceCharge;

    }
}
