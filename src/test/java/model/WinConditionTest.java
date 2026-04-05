package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WinConditionTest {
    @Test
    @DisplayName("보너스 볼이 로또 범위 안에 있음을 확인")
    void testBonusBallInRange() {
        // given
        int bonusBall = LottoSettingsConstants.LOTTO_MAXIMUM_NUMBER + 1;
        List<Integer> winningNumbers = List.of(1,2,3,4,5,6);
        // when
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> new WinCondition(winningNumbers, bonusBall));

        // then
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_OUT_OF_RANGE, exception.getMessage());
    }

    @Test
    @DisplayName("보너스 볼이 당첨 번호와 안 겹치는지 확인")
    void testBonusBallInWinningNumber() {
        // given
        int bonusBall = 1;
        List<Integer> winningNumbers = List.of(1,2,3,4,5,6);

        // when
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> new WinCondition(winningNumbers, bonusBall));

        // then
        Assertions.assertEquals(ErrorMessageConstants.BONUS_NUMBER_IN_WINNING_NUMBER, exception.getMessage());
    }
}
