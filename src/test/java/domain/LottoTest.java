package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("숫자가 주어졌을 경우 정상적으로 객체가 생성된다.")
    void shouldReturnLotto_whenValidNumbers() {
        // given & when & then
        assertThatCode(() -> new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6))
        ))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자의 개수가 적을 때 예외가 발생한다.")
    void shouldThrowException_whenLessLottoCount() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5))
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자의 개수가 많을 때 예외가 발생한다.")
    void shouldThrowException_whenMoreLottoCount() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7))
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("중복된_숫자가_있으면_예외가_발생한다")
    void shouldThrowException_whenDuplicateNumbers() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(3),
                new LottoNumber(5),
                new LottoNumber(6))
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 번호가 있습니다.");
    }
}
