package lotto.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.generator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomLottoGeneratorTest {

    @Test
    @DisplayName("랜덤 로또 번호 생성기는 6개의 로또 번호를 생성한다.")
    void generateLottoNumbers_ShouldReturnSixNumbers() {
        // given
        RandomLottoGenerator generator = new RandomLottoGenerator();

        // when
        List<Integer> lottoNumbers = generator.generateLottoNumbers();

        // then
        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

}
