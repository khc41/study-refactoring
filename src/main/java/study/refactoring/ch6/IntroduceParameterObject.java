package study.refactoring.ch6;

import java.util.List;

public class IntroduceParameterObject {

	public static boolean readingsOutsideRange(Station station, int min, int max) {
		return station.getRecodings().stream()
			.anyMatch(r -> r.getTemp() < min || r.getTemp() > max);
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
