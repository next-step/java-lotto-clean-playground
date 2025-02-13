package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fixture.LottoFixture.testNumbersOneToSix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    @Test
    @DisplayName("OK : 로또를 생성한다.")
    void newLotto() {
        Lotto lotto = new Lotto(testNumbersOneToSix);
        assertThat(lotto.getNumbers()).isEqualTo(testNumbersOneToSix);
    }

    @Test
    @DisplayName("ERROR : 로또 사이즈가 다르면 에러가 발생한다.")
    void newLottoInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5)));
        assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    @Test
    @DisplayName("ERROR : 중복된 로또 번호가 존재하면 에러가 발생한다.")
    void newLottoDuplicateNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 5)));
    }
}