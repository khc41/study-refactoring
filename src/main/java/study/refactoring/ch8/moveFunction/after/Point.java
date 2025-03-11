package study.refactoring.ch8.moveFunction.after;

public class Point {
	private double lat;
	private double lon;

	public Point(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
}
