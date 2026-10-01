package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberGeneratorTest {

    private final LottoNumberGenerator generator = new LottoNumberGenerator();

    @Test
    @DisplayName("번호는 6개다")
    void 번호는_6개다() {
        List<Integer> numbers = generator.generateLottoNumbers();

        assertThat(numbers).hasSize(6);
    }

    @Test
    @DisplayName("번호는 1에서 45 사이다")
    void 번호는_1에서_45_사이다() {
        List<Integer> numbers = generator.generateLottoNumbers();

        assertThat(numbers).allSatisfy(number -> assertThat(number).isBetween(1, 45));
    }

    @Test
    @DisplayName("번호는 중복되지 않는다")
    void 번호는_중복되지_않는다() {
        List<Integer> numbers = generator.generateLottoNumbers();
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        assertThat(uniqueNumbers).hasSameSizeAs(numbers);
    }

    @Test
    @DisplayName("번호는 오름차순으로 정렬된다")
    void 번호는_오름차순으로_정렬된다() {
        List<Integer> numbers = generator.generateLottoNumbers();

        assertThat(numbers).isSorted();
    }
}
