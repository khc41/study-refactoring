package study.refactoring.ch1;

import org.junit.jupiter.api.Test;
import study.refactoring.ch1.data.Invoice;
import study.refactoring.ch1.data.Performance;
import study.refactoring.ch1.data.Play;
import study.refactoring.ch1.data.Plays;
import study.refactoring.ch1.render.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StatementTest {

    Plays plays;
    Invoice invoice;

    @Test
    void printPlaneTextTest() {
        init();
        Statement statement = new Statement();
        String result = statement.statement(invoice, plays);
        assertThat(result).isEqualTo("""
                청구 내역 (고객명: BigCo)
                  Hamlet: $650.00 (55석)
                  As You Like It: $580.00 (35석)
                  Othello: $500.00 (40석)
                총액: $1,730.00
                적립 포인트: 47점
                """);
    }

    @Test
    void printHtmlTest() {
        init();
        Statement statement = new Statement();
        String result = statement.htmlStatement(invoice, plays);
        assertThat(result).isEqualTo(
                """
                        <h1>청구 내역 (고객명: BigCo)</h1>
                        <table>
                        <tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>  <tr><td>Hamlet</td><td>(55석)</td><td>$650.00</td></tr>
                          <tr><td>As You Like It</td><td>(35석)</td><td>$580.00</td></tr>
                          <tr><td>Othello</td><td>(40석)</td><td>$500.00</td></tr>
                        </table>
                        <p>총액: <em>$1,730.00</em>점</p>
                        <p>적립 포인트: <em>47</em>점</p>
                          """);
    }

    void init() {
        Performance performance1 = new Performance("hamlet", 55);
        Performance performance2 = new Performance("as-like", 35);
        Performance performance3 = new Performance("othello", 40);

        invoice = new Invoice("BigCo", List.of(performance1, performance2, performance3));

        Play play1 = new Play("Hamlet", "tragedy");
        Play play2 = new Play("As You Like It", "comedy");
        Play play3 = new Play("Othello", "tragedy");

        Map<String, Play> map = new HashMap<>();
        map.put("hamlet", play1);
        map.put("as-like", play2);
        map.put("othello", play3);
        plays = new Plays(map);
    }

}