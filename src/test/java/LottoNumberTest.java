import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.LottoNumber;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberTest {

    @Test
    void 로또_번호는_1부터_45까지_이어야_한다() {
        // given
        int number = 46;

        // when & then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
    }
}
