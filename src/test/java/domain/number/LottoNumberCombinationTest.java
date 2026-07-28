package domain.number;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberCombinationTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void throwExceptionWhenNumberCountIsNotSix() {
        assertThatThrownBy(() -> LottoNumberCombination.from(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    void throwExceptionWhenNumberIsDuplicated() {
        assertThatThrownBy(() -> LottoNumberCombination.from(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
