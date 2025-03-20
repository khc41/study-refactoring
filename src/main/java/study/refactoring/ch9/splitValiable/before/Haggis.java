package study.refactoring.ch9.splitValiable.before;

public class Haggis {

    public double distanceTravelled(Scenario scenario, double time) {
        double result;
        double acc = scenario.primaryForce / scenario.mass;
        double primaryTime = Math.min(time, scenario.delay);
        result = 0.5 * acc * primaryTime * primaryTime;
        double secondaryTime = time - scenario.delay;
        if(secondaryTime > 0){
            double primaryVelocity = acc * scenario.delay;
            acc = (scenario.primaryForce + scenario.secondaryForce) / scenario.mass;
            result += primaryVelocity * secondaryTime
                    + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }

    public static class Scenario {
        private double primaryForce;
        private double secondaryForce;
        private double mass;
        private double delay;
    }
}
