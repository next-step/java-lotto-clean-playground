package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static model.exception.ExceptionMessage.MANUAL_BUY_COST_OVER_PURCHASE_MONEY_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.MANUAL_BUY_COUNT_INPUT_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.MANUAL_BUY_COUNT_UNDER_ZERO_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualBuyCountTest {

    @DisplayName("입력값이 숫자가 아니면 예외를 발생시킨다.")
    @ValueSource(strings = {"!", "A", "a", "ㄱ", "/", " "})
    @ParameterizedTest
    void check_input_number(String input) {
        // given
        final LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(1000);

        // when
        // then
        assertThatThrownBy(() -> ManualBuyCount.of(input, lottoPurchaseMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MANUAL_BUY_COUNT_INPUT_ERROR_MESSAGE);
    }

    @DisplayName("수동 구입 개수가 구입 금액을 넘어서면 예외를 발생한다.")
    @Test
    void check_input_under_purchase_money() {
        // given
        String input = "2";
        final LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(1000);

        // when
        // then
        assertThatThrownBy(() -> ManualBuyCount.of(input, lottoPurchaseMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MANUAL_BUY_COST_OVER_PURCHASE_MONEY_ERROR_MESSAGE);
    }

    @DisplayName("구입 개수가 음수이면 예외를 발생한다.")
    @Test
    void check_input_under_zero() {
        // given
        String input = "-1";
        final LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(1000);

        // when
        // then
        assertThatThrownBy(() -> ManualBuyCount.of(input, lottoPurchaseMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MANUAL_BUY_COUNT_UNDER_ZERO_ERROR_MESSAGE);
    }
}
