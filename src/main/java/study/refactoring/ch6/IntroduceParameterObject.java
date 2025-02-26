package study.refactoring.ch6;

import java.util.List;

public class IntroduceParameterObject {

	public static boolean readingsOutsideRange(Station station, NumberRange range) {
		return station.getRecodings().stream()
			.anyMatch(r -> !range.contains(r.getTemp()));
	}

	public static class NumberRange {
		private int min;
		private int max;

		public NumberRange(int min, int max) {
			this.min = min;
			this.max = max;
		}

		public boolean contains(int arg) {
			return (arg >= min && arg <= max);
		}

		public int getMin() {
			return min;
		}

		public int getMax() {
			return max;
		}
	}

	public static class Station {
		private String name;
		private List<Recoding> recodings;

		public Station(String name, List<Recoding> recodings) {
			this.name = name;
			this.recodings = recodings;
		}

		public List<Recoding> getRecodings() {
			return recodings;
		}
	}

	public static class Recoding {
		private int temp;
		private String time;

		public Recoding(int temp, String time) {
			this.temp = temp;
			this.time = time;
		}

		public int getTemp() {
			return temp;
		}
	}
}
