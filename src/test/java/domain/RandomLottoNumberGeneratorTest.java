package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;

class RandomLottoNumberGeneratorTest {
    @DisplayName("로또 생성 번호가 1에서 45 사이인지 테스트")
    @Test
    void testLottoNumebrsBetween1And45() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();

        List<Integer> numbers = generator.generateNumbers();

        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @DisplayName("로또 생성 번호가 6개인지 테스트")
    @Test
    void testLottoSize() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();

        List<Integer> numbers = generator.generateNumbers();

        assertThat(numbers).hasSize(6);
    }

    @DisplayName("로또 번호가 중복이 없는지 테스트")
    @Test
    void testLottoDuplicate() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();

        List<Integer> numbers = generator.generateNumbers();

        assertThat(numbers).doesNotHaveDuplicates();
    }
}