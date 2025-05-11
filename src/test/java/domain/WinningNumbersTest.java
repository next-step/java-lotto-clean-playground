package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @Test
    @DisplayName("정상적인 숫자 6개로 WinningNumbers를 생성할 수 있다")
    void createWinningNumbersSuccessfully() {
        assertThatCode(() ->
                new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 5, 6)))
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("6개가 아닌 숫자를 입력하면 예외가 발생한다")
    void throwIfInvalidSize() {
        assertThatThrownBy(() ->
                new WinningNumbers(toWinningNumbers(List.of(1, 2, 3)))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("중복된 숫자가 포함되면 예외가 발생한다")
    void throwIfDuplicatedNumbers() {
        assertThatThrownBy(() ->
                new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 3, 4, 5)))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 1~45 범위를 벗어나면 예외가 발생한다")
    void throwIfOutOfRange() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThatThrownBy(() ->
                        new WinningNumbers(toWinningNumbers(List.of(0, 2, 3, 4, 5, 6)))
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");

        softly.assertThatThrownBy(() ->
                        new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 5, 46)))
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");

        softly.assertAll();
    }

    @Test
    @DisplayName("다른 번호와 몇 개가 일치하는지 반환한다")
    void countMatchingNumbers() {
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumbers other = new LottoNumbers(toLottoNumbers(List.of(3, 4, 5, 7, 8, 9)));

        int matchCount = winningNumbers.countMatch(other);

        assertThat(matchCount).isEqualTo(3);
    }

    private List<WinningNumber> toWinningNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(WinningNumber::new)
                .toList();
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
