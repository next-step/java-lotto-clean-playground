package lotto.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.RandomLottoGenerator;
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

    @Test
    @DisplayName("로또 번호는 1 ~ 45 사이여야 한다")
    void lottoNumbers_ShouldBe_Between_1_And_45() {
        // given
        RandomLottoGenerator lottoGenerator = new RandomLottoGenerator();

        // when
        List<LottoNumber> lottoNumbers = lottoGenerator.generateLottoNumbers();

        // then
        assertThat(lottoNumbers).allSatisfy(number -> {
            assertThat(number.getNumber()).isBetween(1, 45);
        });
    }

}
