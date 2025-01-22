package study.refactoring.calculate;

import study.refactoring.data.Performance;
import study.refactoring.data.Play;

public class PerformanceCalculator {
    protected Performance performance;
    protected Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public static PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy": {
                return new TragedyCalculator(performance, play);
            }
            case "comedy": {
                return new ComedyCalculator(performance, play);
            }
            default: {
                throw new Error(String.format("알 수 없는 장르: %s", play.getType()));
            }
        }
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
                throw new Error("오류 발생");
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
