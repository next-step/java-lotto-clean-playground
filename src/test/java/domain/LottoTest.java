package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    void 로또_숫자의_갯수는_6개가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자의 갯수는 6개입니다.");
    }

    @Test
    void 로또_번호가_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(3),
                new LottoNumber(5),
                new LottoNumber(6)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
