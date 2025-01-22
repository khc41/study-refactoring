package study.refactoring.calculate;

import study.refactoring.data.Performance;
import study.refactoring.data.Play;

public class PerformanceCalculator {
    private Performance performance;
    private Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }



    public int getVolumeCredits() {
        int result = 0;
        result += Math.max(this.performance.getAudience() - 30, 0);
        if ("comedy".equals(this.play.getType())) {
            result += (int) (double) (this.performance.getAudience() / 5);
        }
        return result;
    }

    public int getAmount() {
        int result;
        switch (this.play.getType()) {
            case "tragedy": {
                result = 40000;
                if (this.performance.getAudience() > 30) {
                    result += 1000 * (this.performance.getAudience() - 30);
                }
                break;
            }
            case "comedy": {
                result = 30000;
                if (this.performance.getAudience() > 20) {
                    result += 10000 + 500 * (this.performance.getAudience() - 20);
                }
                result += 300 * this.performance.getAudience();
            }
            break;
            default:
                throw new Error(String.format("알 수 없는 장르: %s", this.play.getType()));
        }
        return result;
    }
}
