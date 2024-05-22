package model.lotto.vo;

import model.lotto.exception.MoneyFormatException;
import model.lotto.exception.MoneyRemainException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoMarketTest {

    @Test
    void 로또_금액_정상_생성() {
        // given
        String input = "1000";

        // when & then
        assertDoesNotThrow(() -> LottoMarket.from(input));
    }

    @Test
    void 로또_생성_갯수_계산() {
        // given
        String input = "1000";

        // when
        LottoMarket money = LottoMarket.from(input);

        // then
        assertThat(money.calculateBoughtLottoSize()).isEqualTo(1);
    }

    @Nested
    class 로또_금액_예외 {

        @ValueSource(strings = {"-1000", "0", "1200abc"})
        @ParameterizedTest(name = "금액이 [{0}]인 경우")
        void 금액이_음수거나_0_또는_문자가_섞이면_예외가_발생한다(final String input) {
            // when & then
            assertThatThrownBy(() -> LottoMarket.from(input))
                    .isInstanceOf(MoneyFormatException.class);
        }

        @Test
        void 금액이_1000으로_나뉘어_떨어지지_않으면_예외가_발생한다() {
            // given
            String input = "1234";

            // when & then
            assertThatThrownBy(() -> LottoMarket.from(input))
                    .isInstanceOf(MoneyRemainException.class);
        }
    }
}
