package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

public class AfricanSwallow extends Bird {
    public AfricanSwallow(BirdInfo bird) {
        super(bird);
    }

    @Override
    public String plumage() {
        return (bird.numberOfCoconuts() > 2) ? "지쳤다" : "보통이다";
    }

    @Override
    public Integer airSpeedVelocity() {
        return 40 - 2 * bird.numberOfCoconuts();
    }
}
