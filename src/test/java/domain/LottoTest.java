package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("자동으로 로또를 생성하면 설정된 개수만큼 랜덤로또번호가 생성된다.")
    @Test
    void createLottoAutomatically() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers()).hasSize(Lotto.LOTTO_NUMBER_COUNT);
    }

    @DisplayName("자동으로 생성된 로또 번호는 1과 45 사이의 값이다.")
    @Test
    void validateNumberRange() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers()).allMatch(number -> number >= Lotto.LOTTO_NUMBER_LOWER_BOUND && number <= Lotto.LOTTO_NUMBER_BOUND);
    }

    @DisplayName("수동으로 로또를 생성한다.")
    @Test
    void createLottoManually() {
        List<Integer> userSelectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(userSelectedNumbers);

        assertThat(lotto.getNumbers()).hasSize(Lotto.LOTTO_NUMBER_COUNT);
        assertThat(lotto.getNumbers()).containsAll(userSelectedNumbers);
    }

    @DisplayName("수동으로 로또를 생성할 때 번호가 6개가 아니면 예외가 발생한다.")
    @Test
    void throwExceptionWhenManualLottoHasInvalidSize() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는" + Lotto.LOTTO_NUMBER_COUNT + "개여야 합니다.");
    }

    @DisplayName("수동으로 로또를 생성할 때 중복된 번호가 있으면 예외가 발생한다.")
    @Test
    void throwExceptionWhenManualLottoHasDuplicateNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
