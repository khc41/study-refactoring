package study.refactoring;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances()
                .stream()
                .map(this::enrichPerformance)
                .toList());
        return renderPlainText(statementData, plays);
    }

    private Performance enrichPerformance(Performance performance) {
        return new Performance(performance.getPlayID(), performance.getAudience());
    }

    private String renderPlainText(StatementData data, Plays plays) {
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", data.getCustomer()));
        for (Performance performance : data.getPerformances()) {
            result.append(String.format("  %s: %s (%s석)\n", playFor(plays, performance).getName(), usd(amountFor(performance, plays)), performance.getAudience()));
        }
        result.append(String.format("총액: %s\n", usd(totalAmount(data, plays))));
        result.append(String.format("적립 포인트: %s점\n", totalVolumeCredits(data, plays)));
        return result.toString();
    }

    private int totalAmount(StatementData data, Plays plays) {
        int result = 0;
        for (Performance performance : data.getPerformances()) {
            result += amountFor(performance, plays);
        }
        return result;
    }

    private int totalVolumeCredits(StatementData data, Plays plays) {
        int result = 0;
        for (Performance performance : data.getPerformances()) {
            result += volumeCreditsFor(plays, performance);
        }
        return result;
    }

    private String usd(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / 100);
    }

    private int volumeCreditsFor(Plays plays, Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(plays, performance).getType())) {
            result += (int) (double) (performance.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Plays plays, Performance performance) {
        return plays.getPlay(performance.getPlayID());
    }

    private int amountFor(Performance performance, Plays plays) {
        int result;
        switch (playFor(plays, performance).getType()) {
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
                throw new Error(String.format("알 수 없는 장르: %s", playFor(plays, performance).getType()));
        }
        return result;
    }
}
