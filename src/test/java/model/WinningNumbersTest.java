package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 정상적으로 생성된다.")
    void createWinningNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

        assertEquals(numbers, winningNumbers.getNumbers(), "당첨 번호가 올바르게 저장되지 않았습니다.");
        assertEquals(bonusNumber, winningNumbers.getBonusNumber(), "보너스 번호가 올바르게 저장되지 않았습니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다.")
    void invalidSizeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5), 6),
                "당첨 번호가 6개가 아닐 때 예외가 발생해야 합니다."
        );
    }

    @Test
    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void duplicateNumbersThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 5), 6),
                "중복된 숫자가 있을 때 예외가 발생해야 합니다."
        );
    }

    @Test
    @DisplayName("당첨 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    void outOfRangeNumbersThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(0, 1, 2, 3, 4, 5), 6),
                "1~45 범위를 벗어난 숫자가 있을 때 예외가 발생해야 합니다."
        );

        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 46), 6),
                "1~45 범위를 벗어난 숫자가 있을 때 예외가 발생해야 합니다."
        );
    }

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    void outOfRangeBonusThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 0),
                "보너스 번호가 1~45 범위를 벗어났을 때 예외가 발생해야 합니다."
        );

        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 46),
                "보너스 번호가 1~45 범위를 벗어났을 때 예외가 발생해야 합니다."
        );
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void duplicateBonusNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                "보너스 번호가 당첨 번호와 중복될 때 예외가 발생해야 합니다."
        );
    }
}
