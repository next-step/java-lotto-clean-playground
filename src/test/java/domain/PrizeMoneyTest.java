package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class PrizeMoneyTest {

    @Test
    @DisplayName("PrizeMoney가_돈을_반환한다")
    void PrizeMoney가_돈을_반환한다() {
        //given
        PrizeMoney prizeMoney = new PrizeMoney(BigDecimal.valueOf(1));

        //when
        BigDecimal amount = prizeMoney.getAmount();

        //then
        assertThat(amount).isEqualTo(BigDecimal.valueOf(1));
    }

    @Test
    @DisplayName("PrizeMoney가_돈을_더한다")
    void PrizeMoney가_돈을_더한다() {
        //given
        PrizeMoney prizeMoney = new PrizeMoney(BigDecimal.valueOf(1));
        PrizeMoney plusMoney = new PrizeMoney(BigDecimal.valueOf(1000));

        //when
        PrizeMoney totalMoney = prizeMoney.plus(plusMoney);

        //then
        assertThat(totalMoney.getAmount()).isEqualTo(BigDecimal.valueOf(1001));
    }

    @Test
    @DisplayName("PrizeMoney가_돈을_곱한다")
    void PrizeMoney가_돈을_곱한다() {
        //given
        PrizeMoney prizeMoney = new PrizeMoney(BigDecimal.valueOf(1000));

        //when
        PrizeMoney totalMoney = prizeMoney.multiply(5);

        //then
        assertThat(totalMoney.getAmount()).isEqualTo(BigDecimal.valueOf(5000));
    }

    @Test
    @DisplayName("음수가_들어오면_예외를_던진다")
    void 음수가_들어오면_예외를_던진다() {
        assertThatThrownBy(() -> new PrizeMoney(BigDecimal.valueOf(-1)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
