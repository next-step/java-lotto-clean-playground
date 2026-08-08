package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

class LottoTest {

    @Test
    @DisplayName("중복된 숫자를 전달했을 때 스스로 설정한 예외가 발생하는지")
    void 중복된_숫자_예외테스트() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 5));
        })
            .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("로또의 숫자 개수가 6개가 아니라면 지정한 예외가 발생하는지")
    void 로또숫자개수_6개_불만족시_테스트() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        })
            .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("숫자가 1부터 45 사이의 범위를 벗어나면 예외가 발생하는지")
    void 로또번호_숫자범위테스트() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 50));
        })
            .isInstanceOf(IllegalArgumentException.class);
    }
}
