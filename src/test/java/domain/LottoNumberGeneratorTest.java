package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberGeneratorTest {

    @Test
    @DisplayName("생성된 로또 번호는 6개여야 한다.")
    void testLottoNumberCount() {
        LottoNumberGenerator generator = new LottoNumberGenerator();
        List<Integer> generatedNumbers = generator.generate();
        int expected = 6;

        int actual = generatedNumbers.size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("생성된 로또 번호에 중복이 없어야 한다.")
    void testLottoNumberDuplicate() {
        LottoNumberGenerator generator = new LottoNumberGenerator();

        List<Integer> generatedNumbers = generator.generate();

        assertThat(generatedNumbers).doesNotHaveDuplicates();

    }

    @Test
    @DisplayName("생성된 로또 번호는 1과 45 사이여야한다.")
    void testLottoNumberInRange() {
        LottoNumberGenerator generator = new LottoNumberGenerator();

        List<Integer> generatedNumbers = generator.generate();

        assertThat(generatedNumbers).allMatch(num -> num >= 1 && num <= 45);
    }

    @Test
    @DisplayName("생성된 로또 번호는 오름차순으로 정렬되어있어야 한다.")
    void testLottoNumberSorted() {
        LottoNumberGenerator generator = new LottoNumberGenerator();

        List<Integer> generatedNumbers = generator.generate();

        assertThat(generatedNumbers).isSorted();
    }
}
