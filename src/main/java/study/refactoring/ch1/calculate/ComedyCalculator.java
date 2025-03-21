package study.refactoring.ch1.calculate;

import study.refactoring.ch1.data.Performance;
import study.refactoring.ch1.data.Play;

public class ComedyCalculator extends PerformanceCalculator {
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    public int getAmount() {
        int result = 30000;
        if (this.performance.getAudience() > 20) {
            result += 10000 + 500 * (this.performance.getAudience() - 20);
        }
        result += 300 * this.performance.getAudience();
        return result;
    }

    public int getVolumeCredits() {
        return super.getVolumeCredits() + (int) (double) (this.performance.getAudience() / 5);
    }
}
