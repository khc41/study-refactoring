package study.refactoring.ch8.replaceLoopWithPipeline.after;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OfficeTest {


    @Test
    void testAcquireData() {
        String input = """
                office, country, telephone
                Chicago, USA, +1 312 373 1000
                Beijing, China, +86 4008 900 505
                Bangalore, India, +91 80 4064 9570
                Porto Alegre, Brazil, +55 51 3079 3550
                Chennai, India, +91 44 660 44766
                """;
        List<Map<String, String>> result = new Office().acquireData(input);

        Assertions.assertAll(
                () -> assertThat(result).contains(
                        Map.of("city", "Bangalore", "phone", "+91 80 4064 9570")),
                () -> assertThat(result).contains(
                        Map.of("city", "Chennai", "phone", "+91 44 660 44766")),
                () -> assertThat(result).hasSize(2)
        );
    }
}