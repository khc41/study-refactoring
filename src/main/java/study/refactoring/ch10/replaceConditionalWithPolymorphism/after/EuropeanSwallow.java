package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

public class EuropeanSwallow extends Bird{
    public EuropeanSwallow(BirdInfo bird) {
        super(bird);
    }

    @Override
    public String plumage() {
        return "보통이다";
    }

    @Override
    public Integer airSpeedVelocity() {
        return 35;
    }
}
