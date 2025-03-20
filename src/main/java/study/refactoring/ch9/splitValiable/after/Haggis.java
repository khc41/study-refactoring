package study.refactoring.ch9.splitValiable.after;

public class Haggis {

    public double distanceTravelled(Scenario scenario, double time) {
        double result;
        final double primaryAcceleration = scenario.primaryForce / scenario.mass;
        double primaryTime = Math.min(time, scenario.delay);
        result = 0.5 * primaryAcceleration * primaryTime * primaryTime;
        double secondaryTime = time - scenario.delay;
        if (secondaryTime > 0) {
            double primaryVelocity = primaryAcceleration * scenario.delay;
            final double secondaryAcceleration = (scenario.primaryForce + scenario.secondaryForce) / scenario.mass;
            result += primaryVelocity * secondaryTime
                    + 0.5 * secondaryAcceleration * secondaryTime * secondaryTime;
        }
        return result;
    }

    public static class Scenario {
        private final double primaryForce;
        private final double secondaryForce;
        private final double mass;
        private final double delay;

        public Scenario(double primaryForce, double secondaryForce, double mass, double delay) {
            this.primaryForce = primaryForce;
            this.secondaryForce = secondaryForce;
            this.mass = mass;
            this.delay = delay;
        }
    }
}
