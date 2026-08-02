package view;

import domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoNumberParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", " 1, 2, 3, 4, 5, 6"})
    void 문자열을_파싱해_로또번호_목록을_생성한다(String input) {
        List<LottoNumber> numbers = LottoNumberParser.parse(input);
        assertEquals(6, numbers.size());
    }

    @Test
    void 숫자가_아닌_값이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> LottoNumberParser.parse("1,2,3,4,5,가"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자만 입력해주세요.");
    }

    @Test
    void 범위를_벗어난_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> LottoNumberParser.parse("1,2,3,4,5,50"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }
}
