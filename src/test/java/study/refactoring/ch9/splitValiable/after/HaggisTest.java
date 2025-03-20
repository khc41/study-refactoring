package study.refactoring.ch9.splitValiable.after;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HaggisTest {

    @Test
    void testDistanceTravelled() {
        Haggis haggis = new Haggis();
        assertThat(haggis.distanceTravelled(new Haggis.Scenario(10, 8, 3, 2), 10))
                .isEqualTo(252);
    }

}