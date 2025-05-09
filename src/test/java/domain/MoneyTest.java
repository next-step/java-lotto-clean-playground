package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("로또를_한장도_구입할수없으면_예외를_던진다")
    void 로또를_한장도_구입할수없으면_예외를_던진다() {

        assertThatThrownBy(() -> Money.from(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또별_가격에_맞지않으면_예외를_던진다")
    void 로또별_가격에_맞지않으면_예외를_던진다() {

        assertThatThrownBy(() -> Money.from(2001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매한 로또의 개수를 반환한다")
    void 구매한_로또의_개수를_반환한다() {
        //given
        Money money = Money.from(5000);

        //when
        int purchasedLottoCount = money.getPurchasedLottoCount();

        //then
        assertThat(purchasedLottoCount).isEqualTo(5);
    }

    @Test
    @DisplayName("구매한_돈의_값을_반환한다")
    void 구매한_돈의_값을_반환한다() {
        //given
        Money money = Money.from(5000);

        //when
        BigDecimal amount = money.getAmount();

        //then
        assertThat(amount).isEqualTo(BigDecimal.valueOf(5000));
    }
}
