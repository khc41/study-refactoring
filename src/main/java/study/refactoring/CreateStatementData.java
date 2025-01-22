package study.refactoring;

public class CreateStatementData {

    public static StatementData createStatementData(Invoice invoice, Plays plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances()
                .stream()
                .map(it -> enrichPerformance(it, plays))
                .toList());
        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData));
        return statementData;
    }

    private static PerformanceData enrichPerformance(Performance performance, Plays plays) {
        PerformanceData result = new PerformanceData(performance.getPlayID(), performance.getAudience());
        result.setPlay(playFor(plays, performance));
        result.setAmount(amountFor(result));
        result.setVolumeCredits(volumeCreditsFor(result));
        return result;
    }

    private static int totalAmount(StatementData data) {
        return data.getPerformances().stream()
                .mapToInt(PerformanceData::getAmount)
                .sum();
    }

    private static int totalVolumeCredits(StatementData data) {
        return data.getPerformances().stream()
                .mapToInt(PerformanceData::getVolumeCredits)
                .sum();
    }

    private static int volumeCreditsFor(PerformanceData performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(performance.getPlay().getType())) {
            result += (int) (double) (performance.getAudience() / 5);
        }
        return result;
    }

    private static Play playFor(Plays plays, Performance performance) {
        return plays.getPlay(performance.getPlayID());
    }

    private static int amountFor(PerformanceData performance) {
        int result;
        switch (performance.getPlay().getType()) {
            case "tragedy": {
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            }
            case "comedy": {
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
            }
            break;
            default:
                throw new Error(String.format("알 수 없는 장르: %s", performance.getPlay().getType()));
        }
        return result;
    }
}
