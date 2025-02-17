package study.refactoring.ch4.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProvinceTest {

    private Province asia;
    private Province noProducers;

    @BeforeEach
    void initAsia() {
        asia = new Province(sampleProvinceData());
    }

    @BeforeEach
    void initNoProducers() {
        noProducers = new Province(sampleNoProducers());
    }

    @Test
    void testProvinceShortfall() {
        assertThat(asia.getShortfall()).isEqualTo(5);
    }

    @Test
    void testProvinceProfit() {
        assertThat(asia.getProfit()).isEqualTo(230);
    }

    @Test
    void testProvinceChangeProduction() {
        asia.getProducers().getFirst().setProduction("20");
        assertThat(asia.getShortfall()).isEqualTo(-6);
        assertThat(asia.getProfit()).isEqualTo(292);
    }

    @Test
    void testNoProducersShortfall() {
        assertThat(noProducers.getShortfall()).isEqualTo(30);
    }

    @Test
    void testNoProducersProfit() {
        assertThat(noProducers.getProfit()).isEqualTo(0);
    }

    @Test
    void testProvinceZeroDemand() {
        asia.setDemand("0");
        assertThat(asia.getShortfall()).isEqualTo(-25);
        assertThat(asia.getProfit()).isEqualTo(0);
    }

    @Test
    void testProvinceNegativeDemand() {
        asia.setDemand("-1");
        assertThat(asia.getShortfall()).isEqualTo(-26);
        assertThat(asia.getProfit()).isEqualTo(-10);
    }

    @Test
    void testProvinceEmptyStringDemand() {
        assertThatThrownBy(() -> asia.setDemand(""))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    void testStringForProducers(){
        Map<String, Object> data = makeProvinceData("String producers", "", 30, 20);
        assertThatThrownBy(()-> new Province(data))
                .isInstanceOf(ClassCastException.class);
    }



    private Map<String, Object> sampleNoProducers() {
        return makeProvinceData("No producers", new ArrayList<>(), 30, 20);
    }

    private Map<String, Object> sampleProvinceData() {
        return makeProvinceData("Asia", makeProducerList(), 30, 20);
    }

    private Map<String, Object> makeProvinceData(String name, Object producers, int demand, int price) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("producers", producers);
        data.put("demand", demand);
        data.put("price", price);
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