package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {
    @DisplayName("로또 생성 번호가 1에서 45 사이인지 테스트")
    @Test
    void testLottoNumebersBetween1And45() {
        LottoGenerator generator = new RandomLottoGenerator();

        List<LottoNumber> numbers = generator.generateNumbers();

        for (LottoNumber number : numbers) {
            assertThat(number.value()).isBetween(1, 45);
        }
    }

    @DisplayName("로또 생성 번호가 6개인지 테스트")
    @Test
    void testLottoSize() {
        LottoGenerator generator = new RandomLottoGenerator();

        List<LottoNumber> numbers = generator.generateNumbers();

        assertThat(numbers).hasSize(6);
    }

    @DisplayName("로또 번호가 중복이 없는지 테스트")
    @Test
    void testLottoDuplicate() {
        LottoGenerator generator = new RandomLottoGenerator();

        List<LottoNumber> numbers = generator.generateNumbers();

        assertThat(numbers).doesNotHaveDuplicates();
    }
}
