package study.refactoring.ch7.inlineClass.after;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    @Test
    void testTrackingInfo() {
        Shipment shipment = new Shipment("대한통운", "123456");

        assertThat(shipment.getTrackingInfo()).isEqualTo("대한통운: 123456");
    }
}