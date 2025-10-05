package io.suhan.lotto.model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class LottoNumberTest {
    @Test
    void 유효한_번호를_생성할_수_있다() {
        // given
        int validNumber = Lotto.LOTTO_NUMBER_MIN;

        // when
        LottoNumber lottoNumber = new LottoNumber(validNumber);

        // then
        assertThat(lottoNumber.getValue()).isEqualTo(validNumber);
    }

    @Test
    void 번호는_범위를_벗어날_수_없다() {
        // given
        String expectedMessage = "로또 번호는 " + Lotto.LOTTO_NUMBER_MIN + "~" + Lotto.LOTTO_NUMBER_MAX + " 사이여야 합니다.";

        // when and then
        SoftAssertions.assertSoftly((softly) -> {
            softly.assertThatThrownBy(() -> new LottoNumber(Lotto.LOTTO_NUMBER_MIN - 1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(expectedMessage);
            softly.assertThatThrownBy(() -> new LottoNumber(Lotto.LOTTO_NUMBER_MAX + 1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(expectedMessage);
        });
    }
}
