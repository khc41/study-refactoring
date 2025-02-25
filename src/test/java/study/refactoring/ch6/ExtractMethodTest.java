package study.refactoring.ch6;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class ExtractMethodTest {

	@Test
	void testOutput() {
		List<ExtractMethod.Order> orders = List.of(new ExtractMethod.Order(3000), new ExtractMethod.Order(4000));
		ExtractMethod.Invoice invoice = new ExtractMethod.Invoice("홍길동", orders);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		ExtractMethod.printOwing(invoice);
		String output = outputStream.toString();
		assertThat(output).contains(
			"""
				****************
				**** 고객 채무 ****
				****************
				고객명: 홍길동
				채무액: 7000
				마감일: 2025-03-27
				"""
		);

	}
}