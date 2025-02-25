package study.refactoring.ch6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InlineMethodTest {

	@ParameterizedTest
	@CsvSource(value = {"6,2", "5,1"})
	void testRating(int numberOfLateDeliveries, int result) {
		InlineMethod.Driver driver = new InlineMethod.Driver(numberOfLateDeliveries);

		assertThat(InlineMethod.rating(driver)).isEqualTo(result);
	}

	@Test
	void testReportLines() {
		InlineMethod.Customer customer = new InlineMethod.Customer("홍길동", "서울시 강남구");
		assertThat(InlineMethod.reportLines(customer)).containsExactly(
			List.of("name", "홍길동"),
			List.of("location", "서울시 강남구")
		);
	}

}