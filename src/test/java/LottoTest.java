import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("정상적인 로또 생성 테스트")
    void createLotto() {
        List<Integer> normalNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> new Lotto(normalNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 개수가 6개가 아니면(5개 이하 또는 7개 이상) 예외가 발생한다.")
    void validateLottoSize() {
        // 5개인 경우
        List<Integer> underSizeNumbers = List.of(1, 2, 3, 4, 5);
        // 7개인 경우
        List<Integer> overSizeNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(() -> new Lotto(underSizeNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");

        assertThatThrownBy(() -> new Lotto(overSizeNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    void validateLottoNumberRange() {
        List<Integer> minExceptionNumbers = List.of(0, 1, 2, 3, 4, 5);
        List<Integer> maxExceptionNumbers = List.of(1, 2, 3, 4, 5, 46);

        assertThatThrownBy(() -> new Lotto(minExceptionNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지여야 합니다.");

        assertThatThrownBy(() -> new Lotto(maxExceptionNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void validateDuplication() {
        List<Integer> duplicateNumbers = List.of(1, 1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new Lotto(duplicateNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 로또 번호가 존재합니다.");
    }
}
