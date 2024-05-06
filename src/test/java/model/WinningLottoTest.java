package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 로또의 보너스 번호는 로또 번호와 겹쳐선 안된다.")
    void validateBonusNumberWithNumbers() {
        final Numbers numbers = new Numbers(List.of(1, 2, 3, 4, 5, 6));
        final int bonusNumber = 6;
        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 로또 번호와 겹쳐선 안됩니다.");
    }

}
