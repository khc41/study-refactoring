package study.refactoring;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StatementTest {

    Plays plays;
    Invoice invoice;

    @Test
    void printTest() {
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