package view;

import lotto.Lotto;
import lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {

    @Test
    @DisplayName("쉼표로 구분한 당첨 번호를 로또로 변환한다")
    void parseLotto() {
        Lotto lotto = InputView.parseLotto("1, 2, 3, 4, 5, 6");
        Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ).forEach(number -> assertThat(lotto.contains(number)).isTrue());
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 변환할 수 없다")
    void rejectInvalidWinningLotto() {
        assertThatThrownBy(() -> InputView.parseLotto("1, 2, 3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호를 로또 번호로 변환한다")
    void parseBonusNumber() {
        LottoNumber bonusNumber = InputView.parseBonusNumber("7");

        assertThat(bonusNumber).isEqualTo(new LottoNumber(7));
    }
}
