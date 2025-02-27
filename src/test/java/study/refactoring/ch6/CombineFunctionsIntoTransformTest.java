package study.refactoring.ch6;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class CombineFunctionsIntoTransformTest {

    @Test
    void checkReadingUnchanged() {
        Map<String, Object> baseReading = CombineFunctionsIntoTransform.acquireReading();
        final Map<String, Object> oracle = new HashMap<>(baseReading);
        CombineFunctionsIntoTransform.enrichReading(baseReading);

        assertThat(baseReading).containsAllEntriesOf(oracle);
    }

}