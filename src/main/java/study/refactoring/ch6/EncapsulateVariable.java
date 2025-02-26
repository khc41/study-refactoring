package study.refactoring.ch6;

public class EncapsulateVariable {

	static Person defaultOwner;

	public static Person defaultOwner() {
		return new Person("마틴", "파울러");
	}

	public static void setDefaultOwner(Person arg) {
		defaultOwner = arg;
	}

	public static class Person {
		private String lastName;
		private String firstName;

		public Person(String lastName, String firstName) {
			this.lastName = lastName;
			this.firstName = firstName;
		}
	}
}
