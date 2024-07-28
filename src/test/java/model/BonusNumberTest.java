package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static model.exception.ExceptionMessage.BONUS_BALL_IN_WINNING_LOTTO_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    @DisplayName("보너스 볼이 당첨 번호에 포함되어 있으면 예외를 발생한다.")
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6"})
    @ParameterizedTest
    void bonus_number_in_winning_lotto(String input) {
        // given
        List<Integer> winningLotto = List.of(1, 2, 3, 4, 5, 6);

        // when
        // then
        assertThatThrownBy(() -> BonusNumber.of(input, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_BALL_IN_WINNING_LOTTO_ERROR_MESSAGE);
    }
}
