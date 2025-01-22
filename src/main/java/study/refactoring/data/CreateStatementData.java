package study.refactoring.data;

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
        PerformanceCalculator calculator = new PerformanceCalculator(performance, playFor(plays, performance));
        PerformanceData result = new PerformanceData(performance.getPlayID(), performance.getAudience());
        result.setPlay(playFor(plays, performance));
        result.setAmount(calculator.getAmount());
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

    private static int amountFor(Performance performance, Plays plays) {
        return new PerformanceCalculator(performance, playFor(plays, performance)).getAmount();
    }
}
