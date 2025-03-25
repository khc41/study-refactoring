package study.refactoring.ch10.decomposeConditional.before;

import java.time.LocalDateTime;

public class SummerCharge {
    private Plan plan;
    private double charge;
    private int quantity;

    public double calculateCharge(LocalDateTime aDate) {
        if (!aDate.isBefore(plan.summerStart) && !aDate.isAfter(plan.summerEnd)) {
            charge = quantity * plan.summerRate;
        } else {
            charge = quantity * plan.regularRate + plan.regularServiceCharge;
        }
        return charge;
    }

    public static class Plan {
        private LocalDateTime summerStart;
        private LocalDateTime summerEnd;
        private double summerRate;
        private double regularRate;
        private double regularServiceCharge;

    }
}
