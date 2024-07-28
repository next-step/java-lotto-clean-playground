package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static model.exception.ExceptionMessage.LOTTO_PURCHASE_MONEY_NOT_DIVIDED_1000_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoPurchaseMoneyTest {

    @DisplayName("구매금액이 음수이면 예외를 발생한다.")
    @Test
    void money_not_under_zero() {
        // given
        int value = -1;
        // when
        // then
        assertThatThrownBy(() -> new LottoPurchaseMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 음수가 될 수 없습니다.");
    }

    @DisplayName("구매금액이 1000단위가 아니면 예외를 발생한다.")
    @ValueSource(ints = {1100, 1010, 1001})
    @ParameterizedTest
    void money_divide_with_1000(int value) {
        // given
        // when
        // then
        assertThatThrownBy(() -> new LottoPurchaseMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_PURCHASE_MONEY_NOT_DIVIDED_1000_ERROR_MESSAGE);
    }

    @DisplayName("로또 구매 개수 구한다.")
    @Test
    void get_lotto_count() {
        // given
        final int pay = 10000;
        final LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(pay);

        // when
        final int result = lottoPurchaseMoney.getPurchaseQuantity();

        // then
        assertThat(result).isEqualTo(10);
    }
}
