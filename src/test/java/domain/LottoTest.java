package domain;

import static domain.Lotto.ERROR_DUPLICATION;
import static domain.Lotto.ERROR_INVALID_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void createLotto() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("로또 번호는 중복될 수 없다")
    void duplicateNumbersTest() {
        List<LottoNumber> numbers = Stream.of(1, 1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .toList();
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_DUPLICATION);
    }

    @Test
    @DisplayName("로또 번호는 6개가 아니면 예외가 발생한다")
    void invalidCountTest() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .toList();
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_SIZE);
    }
}