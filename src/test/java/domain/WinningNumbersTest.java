package domain;

import domain.util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WinningNumbersTest {
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    @Test
    void should_throw_exception_when_bonus_number_is_duplicated() {
        Lotto winning = InputParser.parseLotto("1,2,3,4,5,6");
        LottoNumber bonus = new LottoNumber(6); // 중복

        assertThatThrownBy(() -> new WinningNumbers(winning, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

}