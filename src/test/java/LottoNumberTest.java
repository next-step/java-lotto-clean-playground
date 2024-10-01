import domain.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(
            ints = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24 ,25, 26, 27, 28, 29,
            30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44, 45
            }
    )
    void 로또_번호는_1부터_45_사이를_가질_수_있다(int value) {
        Assertions.assertDoesNotThrow(() -> {
            LottoNumber lottoNumber = new LottoNumber(value);
        });
    }

    @ParameterizedTest
    @ValueSource(
            ints = {
                    0, 46, -1
            }
    )
    void 로또_번호는_1부터_45_사이_외의_숫자를_가질_수_없다(int value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber lottoNumber = new LottoNumber(value);
        });
    }

    @Test
    void 로또_번호는_vo_객체로_value_값이_같으면_동일한_객체로_본다() {
        int value = 10;
        LottoNumber lottoNumber1 = new LottoNumber(value);
        LottoNumber lottoNumber2 = new LottoNumber(value);

        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @Test
    void 로또_번호는_vo_객체로_value_값이_다르면_다른_객체로_본다() {
        int value1 = 10;
        int value2 = 15;
        LottoNumber lottoNumber1 = new LottoNumber(value1);
        LottoNumber lottoNumber2 = new LottoNumber(value2);

        assertThat(lottoNumber1).isNotEqualTo(lottoNumber2);
    }


    @Test
    void 로또_value_값이_큰_객체를_더_큰값으로_파악한다() {
        int value1 = 10;
        int value2 = 15;
        LottoNumber lottoNumber1 = new LottoNumber(value1);
        LottoNumber lottoNumber2 = new LottoNumber(value2);

        assertThat(lottoNumber1).isLessThan(lottoNumber2);
    }


}
