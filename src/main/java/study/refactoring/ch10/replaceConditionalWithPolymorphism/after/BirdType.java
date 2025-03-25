package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

public class BirdType {

    public String plumage(Bird.BirdInfo bird) {
        return createBird(bird).plumage();
    }

    public Integer airSpeedVelocity(Bird.BirdInfo bird) {
        return createBird(bird).airSpeedVelocity();
    }

    public Bird createBird(Bird.BirdInfo bird) {
        return switch (bird.type()) {
            case "유럽 제비" -> new EuropeanSwallow(bird);
            case "아프리카 제비" -> new AfricanSwallow(bird);
            case "노르웨이 파랑 앵무" -> new NorwegianBlueParrot(bird);
            default -> new Bird(bird);
        };
    }
}
