package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoCountTest {

    @Test
    @DisplayName("로또의 수는 음수가 될 수 없다")
    void 로또의_수는_음수가_될_수_없다() {

        assertThatThrownBy(() -> LottoCount.from(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoCount가 옳바른 로또의 개수를 반환한다")
    void LottoCount가_옳바른_로또의_개수를_반환한다() {
        //given
        LottoCount lottoCount = LottoCount.from(10);

        //when
        int getLottoCount = lottoCount.getLottoCount();

        //then
        assertThat(getLottoCount).isEqualTo(10);
    }
}
