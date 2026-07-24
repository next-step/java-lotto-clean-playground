package domain.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("중복 없는 6개의 정렬된 로또 번호를 생성한다")
    void generateSixSortedNumbersWithoutDuplicates() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        List<Integer> lottoNumbers = lottoNumberGenerator.generate();

        assertThat(lottoNumbers).hasSize(6);
        assertThat(lottoNumbers).doesNotHaveDuplicates();
        assertThat(lottoNumbers).isSorted();
    }
}
