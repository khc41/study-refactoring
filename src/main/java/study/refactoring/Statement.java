package study.refactoring;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer());
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance perf : invoice.getPerformances()) {
            final Play play = plays.getPlay(perf.getPlayID());
            int thisAmount;

            thisAmount = amountFor(perf, play);
            // 포인트를 적립한다.
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }

            // 청구 내역을 출력한다.
            result += String.format("  %s: %s (%s석)\n", play.getName(), format.format(thisAmount/100), perf.getAudience());
            totalAmount += thisAmount;
        }
        result += String.format("총액: %s\n", format.format(totalAmount/100));
        result += String.format("적립 포인트: %s점\n", volumeCredits);
        return result;
    }

    private int amountFor(Performance performance, Play play) {
        int result;
        switch (play.getType()) {
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
                throw new Error(String.format("알 수 없는 장르: %s", play.getType()));
        }
        return result;
    }
}
