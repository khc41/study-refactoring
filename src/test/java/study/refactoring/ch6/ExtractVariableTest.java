package study.refactoring.ch6;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ExtractVariableTest {

	@Test
	void testPrice() {
		ExtractVariable.Order order = new ExtractVariable.Order(1000, 10);
		assertThat(ExtractVariable.price(order)).isEqualTo(9850.0);
	}

}