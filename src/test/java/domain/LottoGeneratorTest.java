package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("로또 개수를 구매 금액에 따라 생성한다")
    void generateCorrectNumberOfLottos() {
        NumberGenerator fixedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        TicketGenerator fixedTicketGenerator = amount -> 3;
        LottoGenerator generator = new LottoGenerator(fixedNumberGenerator, fixedTicketGenerator);

        Lottos lottos = generator.generate(3000);

        assertThat(lottos.getLottos()).hasSize(3);
    }

    @Test
    @DisplayName("모든 로또 번호가 고정된 값으로 생성된다")
    void generateLottosWithFixedNumbers() {
        NumberGenerator fixedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        TicketGenerator fixedTicketGenerator = amount -> 3;
        LottoGenerator generator = new LottoGenerator(fixedNumberGenerator, fixedTicketGenerator);

        Lottos lottos = generator.generate(3000);

        lottos.getLottos().forEach(lotto ->
                assertThat(lotto.getNumbers().getNumbers())
                        .map(LottoNumber::value)
                        .containsExactly(1, 2, 3, 4, 5, 6)
        );
    }
}
