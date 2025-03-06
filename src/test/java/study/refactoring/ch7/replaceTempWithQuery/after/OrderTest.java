package study.refactoring.ch7.replaceTempWithQuery.after;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void testGetPrice() {
        Order order = new Order(100, new Item(1000));

        Assertions.assertThat(order.getPrice()).isEqualTo(95000.0);
    }

}