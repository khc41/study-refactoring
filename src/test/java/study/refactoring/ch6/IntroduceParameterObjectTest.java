package study.refactoring.ch6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static study.refactoring.ch6.IntroduceParameterObject.Recoding;
import static study.refactoring.ch6.IntroduceParameterObject.Station;

import java.util.List;

import org.junit.jupiter.api.Test;

class IntroduceParameterObjectTest {

	@Test
	void testReadingsOutsideRange() {
		List<Recoding> recodings = List.of(new Recoding(47, "2016-11-10 09:10"),
			new Recoding(53, "2016-11-10 09:20"),
			new Recoding(58, "2016-11-10 09:30"),
			new Recoding(53, "2016-11-10 09:40"),
			new Recoding(51, "2016-11-10 09:50"));
		Station station = new Station("test", recodings);

		assertAll(
			() -> assertThat(IntroduceParameterObject.readingsOutsideRange(station, 48, 57)).isTrue(),
			() -> assertThat(IntroduceParameterObject.readingsOutsideRange(station, 46, 59)).isFalse()
		);

	}
}