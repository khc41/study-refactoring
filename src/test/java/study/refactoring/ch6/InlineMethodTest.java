package study.refactoring.ch6;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InlineMethodTest {

	@ParameterizedTest
	@CsvSource(value = {"6,2", "5,1"})
	void testInlineMethod(int numberOfLateDeliveries, int result) {
		InlineMethod.Driver driver = new InlineMethod.Driver(numberOfLateDeliveries);

		assertThat(InlineMethod.rating(driver)).isEqualTo(result);
	}

}