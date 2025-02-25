package study.refactoring.ch6;

public class ChangeFunctionDeclaration {

	private static double circum(double radius) {
		return circumference(radius);
	}

	private static double circumference(double radius){
		return 2 * Math.PI * radius;
	}
}
