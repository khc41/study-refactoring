package study.refactoring.ch8.moveFunction.after;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GpsTest {

	@Test
	void testCalculateDistance() {
		Point point1 = new Point(37.567, 126.978);
		Point point2 = new Point(37.123, 126.456);
		Gps gps = new Gps();
		double distance = gps.totalDistance(List.of(point1, point2));

		Assertions.assertThat(distance).isEqualTo(4667.3646718711125);
	}
}