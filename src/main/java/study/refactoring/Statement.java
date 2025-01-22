package study.refactoring;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances()
                .stream()
                .map(it -> enrichPerformance(it, plays))
                .toList());
        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData));
        return renderPlainText(statementData);
    }

    private PerformanceData enrichPerformance(Performance performance, Plays plays) {
        PerformanceData result = new PerformanceData(performance.getPlayID(), performance.getAudience());
        result.setPlay(playFor(plays, performance));
        result.setAmount(amountFor(result));
        result.setVolumeCredits(volumeCreditsFor(result));
        return result;
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", data.getCustomer()));
        for (PerformanceData performance : data.getPerformances()) {
            result.append(String.format("  %s: %s (%s석)\n", performance.getPlay().getName(), usd(performance.getAmount()), performance.getAudience()));
        }
        result.append(String.format("총액: %s\n", usd(data.getTotalAmount())));
        result.append(String.format("적립 포인트: %s점\n", data.getTotalVolumeCredits()));
        return result.toString();
    }

    private int totalAmount(StatementData data) {
        int result = 0;
        for (PerformanceData performance : data.getPerformances()) {
            result += performance.getAmount();
        }
        return result;
    }

    private int totalVolumeCredits(StatementData data) {
        int result = 0;
        for (PerformanceData performance : data.getPerformances()) {
            result += performance.getVolumeCredits();
        }
        return result;
    }

    private String usd(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / 100);
    }

    private int volumeCreditsFor(PerformanceData performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(performance.getPlay().getType())) {
            result += (int) (double) (performance.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Plays plays, Performance performance) {
        return plays.getPlay(performance.getPlayID());
    }

    private int amountFor(PerformanceData performance) {
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
