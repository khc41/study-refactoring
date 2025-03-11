package study.refactoring.ch7.encapsulateRecord.after;

public class Organization {

	private String name;
	private String country;

	public Organization(String name, String country) {
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
