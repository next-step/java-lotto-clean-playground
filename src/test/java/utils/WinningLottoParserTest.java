package utils;

import domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoParserTest {

    @Test
    @DisplayName("정상 입력(당첨번호 + 보너스번호)으로 WinningLotto를 생성할 수 있다.")
    void parseValidInput() {
        WinningLotto result = WinningLottoParser.parse("1,2,3,4,5,6", "7");
        assertThat(result.getBonus().value()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    void parseWithNonNumericBonus() {
        assertThatThrownBy(() -> WinningLottoParser.parse("1,2,3,4,5,6", "abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호 개수가 6개가 아니면 예외가 발생한다.")
    void parseInvalidSize() {
        assertThatThrownBy(() -> WinningLottoParser.parse("1,2,3", "7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호에 숫자가 아닌 값이 포함되면 예외가 발생한다.")
    void parseNonNumericWinningNumber() {
        assertThatThrownBy(() -> WinningLottoParser.parse("1,2,a,4,5,6", "7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.");
    }
}
