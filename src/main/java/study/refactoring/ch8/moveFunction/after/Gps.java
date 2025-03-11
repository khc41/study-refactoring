package study.refactoring.ch8.moveFunction.after;

import java.util.List;
import java.util.Map;

public class Gps {

	public Map<String, Object> trackSummary(List<Point> points) {
		final double totalTime = calculateTime();
		final double totalDistance = totalDistance(points);
		final double pace = totalTime / 60 / totalDistance;
		return Map.of(
			"time", totalTime,
			"distance", totalDistance,
			"pace", pace
		);
	}

	public double totalDistance(List<Point> points) {
		double result = 0;
		for (int i = 1; i < points.size(); i++) {
			result += distance(points.get(i - 1), points.get(i));
		}
		return result;
	}

	private double distance(Point p1, Point p2) {
		final int EARTH_RADIUS = 3959;
		final double dLat = radians(p2.getLat() - p1.getLat());
		final double dLon = radians(p2.getLon() - p1.getLon());
		final double a = Math.pow(Math.sin(dLat / 2), 2)
			+ Math.cos(radians(p2.getLat()))
			* Math.cos(radians(p1.getLat()))
			* Math.pow(Math.sin(dLon / 2), 2);
		final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}

	private double radians(double degrees) {
		return degrees * Math.PI;
	}

	private long calculateTime() {
		// 총 시간 계산
		return 0;
	}
}
