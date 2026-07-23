package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("중복되지 않은 여섯 개의 정렬된 번호를 생성한다")
    void generateSixSortedNumbersWithoutDuplicates() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        List<Integer> lottoNumbers = lottoNumberGenerator.generate();

        assertThat(lottoNumbers).hasSize(6);
        assertThat(lottoNumbers).doesNotHaveDuplicates();
        assertThat(lottoNumbers).isSorted();
    }

    @Test
    @DisplayName("로또 번호는 1에서 45 사이다")
    void generateNumbersBetweenOneAndFortyFive() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        List<Integer> lottoNumbers = lottoNumberGenerator.generate();

        assertThat(lottoNumbers).allMatch(number -> number >= 1);
        assertThat(lottoNumbers).allMatch(number -> number <= 45);
    }
}
