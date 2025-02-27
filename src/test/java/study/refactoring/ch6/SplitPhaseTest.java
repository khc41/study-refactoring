package study.refactoring.ch6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SplitPhaseTest {

    @Test
    void testPriceOrder() {
        SplitPhase.Product product = new SplitPhase.Product(1000, 500, 0.3);
        SplitPhase.ShippingMethod shippingMethod = new SplitPhase.ShippingMethod(30, 50, 30);

        assertThat(SplitPhase.priceOrder(product, 300, shippingMethod)).isEqualTo(309000);
    }
}