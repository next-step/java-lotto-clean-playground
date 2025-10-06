package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    void 유효한_번호면_LottoNumber_객체가_생성된다() {
        // given
        int validNumber = 20;

        // when
        LottoNumber lottoNumber = new LottoNumber(validNumber);

        // then
        assertThat(lottoNumber.getNumber()).isEqualTo(20);
        assertThat(lottoNumber.toString()).isEqualTo("20");
    }

    @Test
    void _1보다_작거나_45보다_크면_예외가_발생한다() {
        // given
        int invalidMin = 0;
        int invalidMax = 46;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(invalidMin));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(invalidMax));
    }

    @Test
    void 같은숫자는_equal비교() {
        //given
        LottoNumber n1 = new LottoNumber(3);
        LottoNumber n2 = new LottoNumber(3);

        //when & then
        assertEquals(n1, n2);
    }
}
