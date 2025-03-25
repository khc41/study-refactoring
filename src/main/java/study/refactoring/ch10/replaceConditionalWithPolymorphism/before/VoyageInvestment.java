package study.refactoring.ch10.replaceConditionalWithPolymorphism.before;

import java.util.List;

public class VoyageInvestment {

    public String rating(Voyage voyage, List<History> history) {
        final int vpf = voyageProfitFactor(voyage, history);
        final int vr = voyageRisk(voyage);
        final int chr = captainHistoryRisk(voyage, history);
        if(vpf * 3 > (vr + chr * 2)){
            return "A";
        }
        return "B";
    }

    private int voyageRisk(Voyage voyage) {
        int result = 1;
        if (voyage.length > 4) {
            result += 2;
        }
        if (voyage.length > 8) {
            result += voyage.length - 8;
        }
        if (List.of("중국", "동인도").contains(voyage.zone)) {
            result += 4;
        }
        return Math.max(result, 0);
    }

    private int captainHistoryRisk(Voyage voyage, List<History> history) {
        int result = 1;
        if (history.size() < 5) {
            result += 4;
        }
        result += (int) history.stream().filter(v -> v.profit < 0).count();
        if ("중국".equals(voyage.zone) && hasChina(history)) {
            result -= 2;
        }
        return Math.max(result, 0);
    }

    private boolean hasChina(List<History> history) {
        return history.stream().anyMatch(v -> "중국".equals(v.zone));
    }

    private int voyageProfitFactor(Voyage voyage, List<History> history) {
        int result = 2;
        if ("중국".equals(voyage.zone)) {
            result += 1;
        }
        if ("동인도".equals(voyage.zone)) {
            result += 1;
        }
        if ("중국".equals(voyage.zone) && hasChina(history)) {
            result += 3;
            if (history.size() > 10) {
                result += 1;
            }
            if (voyage.length > 12) {
                result += 1;
            }
            if (voyage.length < 18) {
                result -= 1;
            }
        } else {
            if (history.size() > 8) {
                result += 1;
            }
            if (voyage.length > 14) {
                result -= 1;
            }
        }
        return result;
    }


    public record Voyage(String zone, int length) {
    }

    public record History(String zone, int profit) {
    }
}