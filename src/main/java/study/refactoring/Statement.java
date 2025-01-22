package study.refactoring;

import java.text.NumberFormat;
import java.util.Locale;

import static study.refactoring.CreateStatementData.createStatementData;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {
        return renderPlainText(createStatementData(invoice, plays));
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

    private String usd(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / 100);
    }

}
