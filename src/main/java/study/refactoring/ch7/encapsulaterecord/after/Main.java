package study.refactoring.ch7.encapsulaterecord.after;

public class Main {

	static final Organization organization = new Organization("애크미 구스베리", "GB");

	public static void main(String[] args) {
		String result = String.format("<h1>%s</h1>", getOrganization().getName());
		getOrganization().setName("newName");
	}

	public static Organization getOrganization() {
		return organization;
	}
}
