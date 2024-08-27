package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberGeneratorTest {
    @DisplayName("로또번호 생성기 테스트 : 6개 수 생성")
    @Test
    public void generatedSizeTest() {
        NumberGenerator randomNumberGerator = new RandomNumberGenerator();
        assertThat(randomNumberGerator.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("로또번호 생성기 테스트 : 오름차순 수 반환")
    @Test
    public void generatedOrderTest() {
        NumberGenerator randomNumberGerator = new RandomNumberGenerator();
        assertThat(randomNumberGerator.getNumbers()).isSorted();
    }
}
