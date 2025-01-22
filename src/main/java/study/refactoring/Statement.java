package study.refactoring;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer());
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.getPerformances()) {
            volumeCredits += volumeCreditsFor(plays, performance);

            // 청구 내역을 출력한다.
            result += String.format("  %s: %s (%s석)\n", playFor(plays, performance).getName(), format.format(amountFor(performance, plays) / 100), performance.getAudience());
            totalAmount += amountFor(performance, plays);
        }
        result += String.format("총액: %s\n", format.format(totalAmount / 100));
        result += String.format("적립 포인트: %s점\n", volumeCredits);
        return result;
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
