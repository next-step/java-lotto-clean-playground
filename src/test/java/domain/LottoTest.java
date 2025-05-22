package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    @Test
    void should_throw_exception_when_lotto_numbers_are_not_six() {
        List<LottoNumber> five = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(five))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개");
    }

    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다")
    @Test
    void should_throw_exception_when_lotto_numbers_are_duplicated() {
        List<LottoNumber> duplicated = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}