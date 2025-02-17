package study.refactoring.ch1.calculate;

import study.refactoring.ch1.data.Performance;
import study.refactoring.ch1.data.Play;

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
        return Math.max(this.performance.getAudience() - 30, 0);
    }

    public int getAmount() {
        throw new Error("서브 클래스에서 처리하도록 설계되었습니다.");
    }
}
