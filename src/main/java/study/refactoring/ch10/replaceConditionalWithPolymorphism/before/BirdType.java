package study.refactoring.ch10.replaceConditionalWithPolymorphism.before;

public class BirdType {

    public String plumage(BirdInfo bird) {
        return switch (bird.type) {
            case "유럽제비" -> "보통이다";
            case "아프리카 제비" -> (bird.numberOfCoconuts > 2) ? "지쳤다" : "보통이다";
            case "노르웨이 파랑 앵무" -> (bird.voltage > 100) ? "그을렸다" : "예쁘다";
            default -> "알 수 없다";
        };
    }

    public Integer airSpeedVelocity(BirdInfo bird) {
        return switch (bird.type) {
            case "유럽제비" -> 35;
            case "아프리카 제비" -> 40 - 2 * bird.numberOfCoconuts;
            case "노르웨이 파랑 앵무" -> (bird.isNailed) ? 0 : 10 + bird.voltage / 10;
            default -> null;
        };
    }


    public static class BirdInfo {
        private String type;
        private int numberOfCoconuts;
        private int voltage;
        private boolean isNailed;
    }
}
