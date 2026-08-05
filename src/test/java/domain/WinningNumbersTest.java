package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningNumbersTest {
    @Test
    @DisplayName("당첨 번호가 정확히 6개면 생성된다")
    void createsWinningNumbersWithSixNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertDoesNotThrow(() -> new WinningNumbers(numbers));
    }

    @Test
    @DisplayName("당첨 번호의 개수가 6개가 아니면 예외가 발생한다")
    void throwsExceptionWhenWinningNumberCountIsInvalid() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        assertThrows(
                IllegalArgumentException.class,
                () -> new WinningNumbers(numbers)
        );
    }

    @Test
    @DisplayName("당첨 번호에 중복된 번호가 있으면 예외가 발생한다")
    void throwsExceptionWhenWinningNumbersContainDuplicates() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThrows(
                IllegalArgumentException.class,
                () -> new WinningNumbers(numbers)
        );
    }
}
