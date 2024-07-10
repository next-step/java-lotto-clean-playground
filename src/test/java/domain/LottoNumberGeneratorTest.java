package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    @Nested
    @DisplayName("로또 숫자 생성 테스트")
    class generateLotto {

        @Test()
        @DisplayName("로또 숫자로는 총 6개의 숫자가 만들어진다.")
        void generateLottosTest() {
            // given
            final List<Integer> generatedNumber = lottoNumberGenerator.generate();
            // when
            // then
            assertThat(generatedNumber.size())
                .isEqualTo(Lotto.SIZE);
        }


    }

}
