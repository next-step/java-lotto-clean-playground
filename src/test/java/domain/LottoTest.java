package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호를 문자열로 출력한다")
    void convertLottoNumbersToString() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        String result = lotto.toString();

        assertThat(result).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void throwExceptionWhenNumberCountIsNotSix() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 1보다 작으면 예외가 발생한다")
    void throwExceptionWhenNumberIsLessThanOne() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 45보다 크면 예외가 발생한다")
    void throwExceptionWhenNumberIsGreaterThanFortyFive() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    void throwExceptionWhenNumberIsDuplicated() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
