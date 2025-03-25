package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

import java.util.List;

public class Rating {
    protected final VoyageInvestment.Voyage voyage;
    protected final List<VoyageInvestment.History> history;

    public Rating(VoyageInvestment.Voyage voyage, List<VoyageInvestment.History> history) {
        this.voyage = voyage;
        this.history = history;
    }

    public String getValue(){
        final int vpf = voyageProfitFactor();
        final int vr = voyageRisk();
        final int chr = captainHistoryRisk();
        if(vpf * 3 > (vr + chr * 2)){
            return "A";
        }
        return "B";
    }

    public int voyageRisk() {
        int result = 1;
        if (voyage.length() > 4) {
            result += 2;
        }
        if (voyage.length() > 8) {
            result += voyage.length() - 8;
        }
        if (List.of("중국", "동인도").contains(voyage.zone())) {
            result += 4;
        }
        return Math.max(result, 0);
    }

    public int captainHistoryRisk() {
        int result = 1;
        if (history.size() < 5) {
            result += 4;
        }
        result += (int) history.stream().filter(v -> v.profit() < 0).count();
        return Math.max(result, 0);
    }

    public boolean hasChina() {
        return history.stream().anyMatch(v -> "중국".equals(v.zone()));
    }

    public int voyageProfitFactor() {
        int result = 2;
        if ("중국".equals(voyage.zone())) {
            result += 1;
        }
        if ("동인도".equals(voyage.zone())) {
            result += 1;
        }
        result += getHistoryLengthFactor();
        result += getVoyageLengthFactor();
        return result;
    }

    public int getVoyageLengthFactor() {
        return voyage.length() > 14 ? -1 : 0;
    }

    public int getHistoryLengthFactor() {
        return history.size() > 8 ? 1 : 0;
    }
}
