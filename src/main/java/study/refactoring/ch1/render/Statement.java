package study.refactoring.ch1.render;

import study.refactoring.ch1.data.Invoice;
import study.refactoring.ch1.data.PerformanceData;
import study.refactoring.ch1.data.Plays;
import study.refactoring.ch1.data.StatementData;

import java.text.NumberFormat;
import java.util.Locale;

import static study.refactoring.ch1.data.CreateStatementData.createStatementData;

public class Statement {

    public String statement(Invoice invoice, Plays plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", data.getCustomer()));
        for (PerformanceData performance : data.getPerformances()) {
            result.append(String.format("  %s: %s (%d석)\n", performance.getPlay().getName(), usd(performance.getAmount()), performance.getAudience()));
        }
        result.append(String.format("총액: %s\n", usd(data.getTotalAmount())));
        result.append(String.format("적립 포인트: %s점\n", data.getTotalVolumeCredits()));
        return result.toString();
    }

    public String htmlStatement(Invoice invoice, Plays plays) {
        return renderHtml(createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("<h1>청구 내역 (고객명: %s)</h1>\n", data.getCustomer()));
        result.append("<table>\n");
        result.append("<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>");
        for (PerformanceData performance : data.getPerformances()) {
            result.append(String.format("  <tr><td>%s</td><td>(%d석)</td>", performance.getPlay().getName(), performance.getAudience()));
            result.append(String.format("<td>%s</td></tr>\n", usd(performance.getAmount())));
        }
        result.append("</table>\n");
        result.append(String.format("<p>총액: <em>%s</em>점</p>\n", usd(data.getTotalAmount())));
        result.append(String.format("<p>적립 포인트: <em>%s</em>점</p>\n", data.getTotalVolumeCredits()));
        return result.toString();
    }

    private String usd(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / 100);
    }

}
