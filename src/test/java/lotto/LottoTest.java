package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    @DisplayName("중복 없는 로또 번호 6개를 가진 로또를 생성한다")
    void createLotto() {
        assertThatCode(() -> new Lotto(Arrays.asList(
                number(1), number(2), number(3),
                number(4), number(5), number(6))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 생성할 수 없다")
    void rejectInvalidSize() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(number(1), number(2))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 번호가 있으면 로또를 생성할 수 없다")
    void rejectDuplicateNumber() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(
                number(1), number(1), number(2),
                number(3), number(4), number(5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private LottoNumber number(int value) {
        return new LottoNumber(value);
    }
}
