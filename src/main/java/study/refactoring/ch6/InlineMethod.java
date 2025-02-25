package study.refactoring.ch6;

public class InlineMethod {

	public static int rating(Driver aDriver) {
		return aDriver.numberOfLateDeliveries > 5 ? 2 : 1;
	}

	public static class Driver {
		private int numberOfLateDeliveries;

		public Driver(int numberOfLateDeliveries) {
			this.numberOfLateDeliveries = numberOfLateDeliveries;
		}
	}
}
