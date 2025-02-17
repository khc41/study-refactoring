package study.refactoring.ch4.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ProvinceTest {

    @Test
    void testShortfall() {
        final Province asia = new Province(sampleProvinceData());
        assertThat(asia.getShortfall()).isEqualTo(5);
    }

    private Map<String, Object> sampleProvinceData() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Asia");
        List<Map<String, Object>> producerList = makeProducerList();
        data.put("producers", producerList);
        data.put("demand", 30);
        data.put("price", 20);
        return data;
    }

    private List<Map<String, Object>> makeProducerList() {
        List<Map<String, Object>> producerList = new ArrayList<>();
        producerList.add(makeProducer("Byzantium", 10, 9));
        producerList.add(makeProducer("Attalia", 12, 10));
        producerList.add(makeProducer("Sinope", 10, 6));
        return producerList;
    }

    private HashMap<String, Object> makeProducer(String name, int cost, int production) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("cost", cost);
        map.put("production", production);
        return map;
    }
}