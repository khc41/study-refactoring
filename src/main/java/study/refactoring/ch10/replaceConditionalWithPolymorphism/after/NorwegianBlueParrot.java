package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

public class NorwegianBlueParrot extends Bird {
    public NorwegianBlueParrot(BirdInfo bird) {
        super(bird);
    }

    @Override
    public String plumage() {
        return (bird.voltage() > 100) ? "그을렸다" : "예쁘다";
    }

    @Override
    public Integer airSpeedVelocity() {
        return (bird.isNailed()) ? 0 : 10 + bird.voltage() / 10;
    }
}
