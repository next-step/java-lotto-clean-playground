package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    @DisplayName("1~45 범위를 벗어나면 예외가 발생한다")
    void 범위를_벗어나면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
    }

    @Test
    @DisplayName("같은 숫자는 동일한 값으로 취급된다")
    void 같은_숫자는_동일한_값으로_취급된다() {
        assertThat(new LottoNumber(5)).isEqualTo(new LottoNumber(5));
    }
}
