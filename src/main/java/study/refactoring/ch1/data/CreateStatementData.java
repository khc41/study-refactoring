package study.refactoring.ch1.data;

import study.refactoring.ch1.calculate.PerformanceCalculator;

import static study.refactoring.ch1.calculate.PerformanceCalculator.createPerformanceCalculator;

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
        PerformanceCalculator calculator = createPerformanceCalculator(performance, playFor(plays, performance));
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

    private static Play playFor(Plays plays, Performance performance) {
        return plays.getPlay(performance.getPlayID());
    }
}
