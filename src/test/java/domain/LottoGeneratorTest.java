package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("구매 금액에 따라 지정된 수의 로또를 생성한다")
    void generateLottosBasedOnAmount() {
        // given
        NumberGenerator fixedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        TicketGenerator fixedTicketGenerator = amount -> 3;

        LottoGenerator generator = new LottoGenerator(fixedNumberGenerator, fixedTicketGenerator);

        // when
        Lottos lottos = generator.generate(3000);

        // then
        assertThat(lottos.getLottos()).hasSize(3);
        assertThat(lottos.getLottos())
                .allSatisfy(lotto ->
                        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6)
                );
    }
}
