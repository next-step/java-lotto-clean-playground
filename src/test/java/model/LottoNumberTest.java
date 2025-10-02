package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("model.LottoNumber 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoNumberTest {

    @Test
    void LottoNumber_생성_시_1에서_45_사이의_값이면_성공한다() {
        int validNumber = 45;
        assertDoesNotThrow(() -> new LottoNumber(validNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void LottoNumber_생성_시_1보다_작은_값이면_예외가_발생한다(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {46, 100})
    void LottoNumber_생성_시_45보다_큰_값이면_예외가_발생한다(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
