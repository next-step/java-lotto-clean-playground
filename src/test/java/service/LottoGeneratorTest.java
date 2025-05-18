package service;

import domain.LottoNumber;
import domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("3장의 로또를 생성하면 리스트 크기는 3이 된다")
    void generateCorrectNumberOfLottos() {
        // given
        NumberGenerator fixedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        LottoGenerator generator = new LottoGenerator(fixedNumberGenerator);

        // when
        Lottos lottos = generator.generate(3);

        // then
        assertThat(lottos.getLottos()).hasSize(3);
    }

    @Test
    @DisplayName("생성된 모든 로또 번호가 고정된 값으로 구성된다")
    void generateLottosWithFixedNumbers() {
        // given
        NumberGenerator fixedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        LottoGenerator generator = new LottoGenerator(fixedNumberGenerator);

        // when
        Lottos lottos = generator.generate(3);

        // then
        lottos.getLottos().forEach(lotto ->
                assertThat(lotto.getNumbers())
                        .map(LottoNumber::value)
                        .containsExactly(1, 2, 3, 4, 5, 6)
        );
    }
}
