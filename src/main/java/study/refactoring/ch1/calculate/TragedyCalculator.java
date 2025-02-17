package study.refactoring.ch1.calculate;

import study.refactoring.ch1.data.Performance;
import study.refactoring.ch1.data.Play;

public class TragedyCalculator extends PerformanceCalculator {
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    public int getAmount() {
        int result = 40000;
        if (this.performance.getAudience() > 30) {
            result += 1000 * (this.performance.getAudience() - 30);
        }
        return result;
    }
}
