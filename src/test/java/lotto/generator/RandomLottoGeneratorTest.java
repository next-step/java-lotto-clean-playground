package lotto.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomLottoGeneratorTest {

    @Test
    @DisplayName("랜덤 로또 번호 생성기는 6개의 로또 번호를 생성한다.")
    void generateLottoNumbers_ShouldReturnSixNumbers() {
        // given
        LottoNumberGenerator stubGenerator = () -> List.of(
            LottoNumber.valueOf(1), LottoNumber.valueOf(2),
            LottoNumber.valueOf(3), LottoNumber.valueOf(4),
            LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );

        // when
        List<LottoNumber> result = stubGenerator.generateLottoNumbers();

        // then
        assertThat(result).hasSize(6)
            .containsExactly(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
            );
    }

}
