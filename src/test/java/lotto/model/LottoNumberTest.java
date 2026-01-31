package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호에 올바른 값이 들어가면 정상 생성된다.")
    void create_Valid_LottoNumber() {
        // given
        int validNumber = 25;

        // when
        LottoNumber lottoNumber = LottoNumber.valueOf(validNumber);

        // then
        assert(lottoNumber.toString().equals("25"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호에 1보다 작거나 45보다 큰 값이 들어가면 IllegalArgumentException이 발생한다.")
    void create_Invalid_LottoNumber(int invalidNumber) {
        // when & then
        try {
            LottoNumber.valueOf(invalidNumber);
            assert(false); // 예외가 발생하지 않으면 실패
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("로또 번호는 1부터 45 사이의 숫자여야 합니다."));
        }
    }

    @Test
    @DisplayName("valueOf 메서드로 생성한 숫자들은 같은 숫자이면 캐싱된 값을 재사용한다.")
    void valueOf_Returns_Cached_Instance() {
        // given
        int number = 7;

        // when
        LottoNumber firstCall = LottoNumber.valueOf(number);
        LottoNumber secondCall = LottoNumber.valueOf(number);

        // then
        assertThat(firstCall).isSameAs(secondCall);
    }

}
