package study.refactoring.ch10.replaceConditionalWithPolymorphism.after;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BirdTypeTest {

    @Test
    @DisplayName("유럽제비 출력 테스트")
    void testEuropeanSwallow() {
        BirdType birdType = new BirdType();
        assertThat(birdType.plumage(new Bird.BirdInfo("유럽 제비", 0, 0, false)))
                .isEqualTo("보통이다");
    }

    @Test
    @DisplayName("아프리카제비 출력 테스트")
    void testAfricanSwallow() {
        BirdType birdType = new BirdType();
        assertThat(birdType.plumage(new Bird.BirdInfo("아프리카 제비", 3, 0, false)))
                .isEqualTo("지쳤다");
    }

    @Test
    @DisplayName("노르웨이파랑앵무 출력 테스트")
    void testNorwegianBlueParrot() {
        BirdType birdType = new BirdType();
        assertThat(birdType.plumage(new Bird.BirdInfo("노르웨이 파랑 앵무", 0, 101, false)))
                .isEqualTo("그을렸다");
    }
}