package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

import java.util.List;

public class VoyageInvestment {

    public Rating createRating(Voyage voyage, List<History> history) {
        if ("중국".equals(voyage.zone) && history.stream().anyMatch(v -> "중국".equals(v.zone))) {
            return new ExperiencedChinaRating(voyage, history);
        }
        return new Rating(voyage, history);
    }

    public String rating(Voyage voyage, List<History> history) {
        return createRating(voyage, history).getValue();
    }

    public record Voyage(String zone, int length) {
    }

    public record History(String zone, int profit) {
    }
}