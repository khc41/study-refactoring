package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

import java.util.List;

public class ExperiencedChinaRating extends Rating {
    public ExperiencedChinaRating(VoyageInvestment.Voyage voyage, List<VoyageInvestment.History> history) {
        super(voyage, history);
    }

    @Override
    public int captainHistoryRisk() {
        final int result = super.captainHistoryRisk() - 2;
        return Math.max(result, 0);
    }

    @Override
    public int voyageProfitFactor() {
        return super.voyageProfitFactor() + 3;
    }

    @Override
    public int getVoyageLengthFactor() {
        int result = 0;
        if (voyage.length() > 12) {
            result += 1;
        }
        if (voyage.length() < 18) {
            result -= 1;
        }
        return result;
    }

    @Override
    public int getHistoryLengthFactor() {
        return this.history.size() > 10 ? 1 : 0;
    }
}
