package study.refactoring.ch6;

import java.util.HashMap;
import java.util.Map;

public class CombineFunctionsIntoTransform {
    private final Map<String, Object> aReading = acquireReading();
    private final double baseCharge = baseRate((int)aReading.get("month"), (int)aReading.get("year")) * (int)aReading.get("quantity");

    public static class Client1 {
        private final Map<String, Object> aReading = acquireReading();
        private final double baseCharge = baseRate((int)aReading.get("month"), (int)aReading.get("year")) * (int)aReading.get("quantity");
    }

    public static class Client2 {
        private final Map<String, Object> aReading = acquireReading();
        private final double base = (baseRate((int)aReading.get("month"), (int)aReading.get("year")) * (int)aReading.get("quantity"));
        private final double taxableCharge = Math.max(0, base - taxThreshold((int)aReading.get("year")));

    }

    public static class Client3 {
        private final Map<String, Object> aReading = acquireReading();
        private final double baseChargeAmount = calculateBaseCharge(aReading);

        private double calculateBaseCharge(Map<String, Object> aReading) {
            return baseRate((int)aReading.get("month"), (int)aReading.get("year")) * (int)aReading.get("quantity");
        }
    }

    private static double taxThreshold(int year) {
        return 0;
    }

    private static double baseRate(int month, int year) {
        // some calculate logic
        return 0;
    }

    private static Map<String, Object> acquireReading() {
        Map<String, Object> map = new HashMap<>();
        map.put("customer", "ivan");
        map.put("quantity", 10);
        map.put("month", 5);
        map.put("year", 2017);
        return map;
    }
}