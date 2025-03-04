package study.refactoring.ch7.encapsulaterecord.before;

import java.util.HashMap;
import java.util.Map;

public class Main {

	static final Map<String, Object> organization = new HashMap<>(Map.of(
		"name", "애크미 구스베리",
		"country", "GB"
	));

	public static void main(String[] args) {
		String result = String.format("<h1>%s</h1>", organization.get("name"));
		organization.put("name", "newName");
	}
}
