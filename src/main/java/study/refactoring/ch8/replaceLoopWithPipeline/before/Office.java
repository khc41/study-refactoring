package study.refactoring.ch8.replaceLoopWithPipeline.before;

import java.util.HashMap;
import java.util.Map;

public class Office {

    public Map<String, Object> acquireData(String input) {
        final String[] lines = input.split("\n");
        boolean firstLine = true;
        final Map<String, Object> result = new HashMap<>();
        for (String line : lines) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] record = line.split(",");
            if ("India".equals(record[1].trim())) {
                result.put("city", record[0].trim());
                result.put("phone", record[2].trim());
            }
        }
        return result;
    }
}
