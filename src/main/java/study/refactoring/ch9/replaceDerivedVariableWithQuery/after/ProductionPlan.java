package study.refactoring.ch9.replaceDerivedVariableWithQuery.after;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {
    private int production;
    private List<Adjustment> adjustments = new ArrayList<>();

    public void applyAdjustment(Adjustment anAdjustment) {
        this.adjustments.add(anAdjustment);
    }

    public int getCalculateProduction() {
        return this.adjustments.stream()
                .mapToInt(Adjustment::getAmount)
                .sum();
    }

    public int getProduction() {
        return getCalculateProduction();
    }

    public static class Adjustment {
        private int amount;

        public int getAmount() {
            return amount;
        }
    }
}
