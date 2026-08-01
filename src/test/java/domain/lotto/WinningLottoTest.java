package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("보너스 볼이 당첨 번호와 중복되면 예외가 발생한다")
    void throwExceptionWhenBonusBallIsDuplicatedWithWinningNumber() {
        assertThatThrownBy(() -> WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                BonusBall.from(6)
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
    }
}
