package study.refactoring.ch9.replaceDerivedVariableWithQuery.before;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {
    private int production;
    private List<Adjustment> adjustments = new ArrayList<>();

    public void applyAdjustment(Adjustment anAdjustment) {
        this.adjustments.add(anAdjustment);
        this.production += anAdjustment.amount;
    }

    public int getProduction() {
        return production;
    }

    public static class Adjustment {
        private int amount;
    }
}
