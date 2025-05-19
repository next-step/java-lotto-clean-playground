package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoNumberTest {

    @Test
    @DisplayName("로또 번호가 1보다 작거나 45보다 크면 예외 발생")
    void lottoNumber_outOfRange() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이여야 합니다.");

        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("정상 범위 내의 번호는 생성 가능")
    void lottoNumber_validRange() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("같은 값을 가진 LottoNumber는 동등하다")
    void lottoNumber_equals_sameValue() {
        LottoNumber num1 = new LottoNumber(10);
        LottoNumber num2 = new LottoNumber(10);

        assertThat(num1).isEqualTo(num2);
        assertThat(num1.hashCode()).isEqualTo(num2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 LottoNumber는 다르다")
    void lottoNumber_equals_differentValue() {
        LottoNumber num1 = new LottoNumber(10);
        LottoNumber num2 = new LottoNumber(11);

        assertThat(num1).isNotEqualTo(num2);
    }

    @Test
    @DisplayName("toString은 숫자 값을 문자열로 반환한다")
    void lottoNumber_toString_shouldReturnNumberAsString() {
        LottoNumber num = new LottoNumber(15);
        assertThat(num.toString()).isEqualTo("15");
    }
}
