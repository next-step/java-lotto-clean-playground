package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {

    @Test
    @DisplayName("쉼표로 구분한 당첨 번호를 로또로 변환한다")
    void parseWinningLotto() {
        assertThatCode(() -> InputView.parseWinningLotto("1, 2, 3, 4, 5, 6"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 변환할 수 없다")
    void rejectInvalidWinningLotto() {
        assertThatThrownBy(() -> InputView.parseWinningLotto("1, 2, 3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호를 로또 번호로 변환한다")
    void parseBonusNumber() {
        assertThatCode(() -> InputView.parseBonusNumber("7"))
                .doesNotThrowAnyException();
    }
}
