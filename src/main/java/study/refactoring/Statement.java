package study.refactoring;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {

        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer()));
        for (Performance performance : invoice.getPerformances()) {

            // 청구 내역을 출력한다.
            result.append(String.format("  %s: %s (%s석)\n", playFor(plays, performance).getName(), usd(amountFor(performance, plays)), performance.getAudience()));

        }

        result.append(String.format("총액: %s\n", usd(totalAmount(invoice, plays))));
        result.append(String.format("적립 포인트: %s점\n", totalVolumeCredits(invoice, plays)));
        return result.toString();
    }

    private int totalAmount(Invoice invoice, Plays plays) {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            totalAmount += amountFor(performance, plays);
        }
        return totalAmount;
    }

    private int totalVolumeCredits(Invoice invoice, Plays plays) {
        int volumeCredits = 0;
        for (Performance performance : invoice.getPerformances()) {
            volumeCredits += volumeCreditsFor(plays, performance);
        }
        return volumeCredits;
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
