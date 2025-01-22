package study.refactoring.data;

import study.refactoring.calculate.PerformanceCalculator;

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
        result.setVolumeCredits(calculator.getVolumeCredits());
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

    private static int volumeCreditsFor(Performance performance, Plays plays) {
        return new PerformanceCalculator(performance, playFor(plays, performance)).getVolumeCredits();
    }

    private static Play playFor(Plays plays, Performance performance) {
        return plays.getPlay(performance.getPlayID());
    }

    private static int amountFor(Performance performance, Plays plays) {
        return new PerformanceCalculator(performance, playFor(plays, performance)).getAmount();
    }
}
