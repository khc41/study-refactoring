package study.refactoring.ch8.replaceLoopWithPipeline.after;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Office {
    public List<Map<String, String>> acquireData(String input) {
        final String[] lines = input.split("\n");
        return Arrays.stream(lines)
                .skip(1)
                .filter(line -> !line.isEmpty())
                .map(line -> line.split(","))
                .filter(record -> "India".equals(record[1].trim()))
                .map(record ->
                        Map.of("city", record[0].trim(),
                                "phone", record[2].trim())
                ).toList();
    }
}
