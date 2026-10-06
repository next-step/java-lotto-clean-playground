package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {

    @Test
    void returnsSecondWhenFiveNumbersAndBonusMatch() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto ticket = Lotto.from(List.of(1, 2, 3, 4, 5, 7));

        assertThat(winningLotto.match(ticket)).isEqualTo(Rank.SECOND);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    void throwsExceptionWhenBonusDuplicatesWinningNumber(int bonusNumber) {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void throwsExceptionWhenBonusIsOutOfRange(int bonusNumber) {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
