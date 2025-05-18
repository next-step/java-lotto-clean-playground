package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoNumberGeneratorTest {

    private final LottoNumberGenerator generator = new LottoNumberGenerator();

    @RepeatedTest(10)
    @DisplayName("로또 번호는 6개이며 1부터 45 사이의 값이다")
    void generateLottoGetNumbersInValidRange() {
        List<Integer> numbers = generator.generate();

        assertThat(numbers).hasSize(6);
        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers).allSatisfy(number ->
                assertThat(number).isBetween(1, 45)
        );
    }

    @RepeatedTest(10)
    @DisplayName("로또 번호는 오름차순으로 정렬되어 있다")
    void generateSortedGetNumbers() {
        List<Integer> numbers = generator.generate();

        List<Integer> sorted = numbers.stream().sorted().toList();
        assertThat(numbers).containsExactlyElementsOf(sorted);
    }
}
