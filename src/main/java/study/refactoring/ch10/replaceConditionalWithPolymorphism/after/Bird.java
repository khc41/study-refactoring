package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

public class Bird {
    protected final BirdInfo bird;

    public Bird(BirdInfo bird) {
        this.bird = bird;
    }

    public String plumage() {
        return "알 수 없다";
    }

    public Integer airSpeedVelocity() {
        return null;
    }


    public record BirdInfo(String type, int numberOfCoconuts, int voltage, boolean isNailed) {
    }
}
