import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    void validateNumber() {
        // 유효한 숫자인 경우
        assertThat(new LottoNumber(1)).isNotNull();
        assertThat(new LottoNumber(45)).isNotNull();

        // 유효하지 않은 숫자인 경우
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1부터 45 사이의 값이어야 합니다.");
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1부터 45 사이의 값이어야 합니다.");
    }

    @Test
    void generate() {
        LottoNumber number = LottoNumber.generate();
        assertThat(number.number()).isBetween(1, 45);
    }

    @Test
    void toString_test() {
        LottoNumber number = new LottoNumber(5);
        assertThat(number.toString()).isEqualTo("5");
    }

    @Test
    void compareTo() {
        LottoNumber num1 = new LottoNumber(1);
        LottoNumber num2 = new LottoNumber(2);
        LottoNumber num3 = new LottoNumber(3);

        assertThat(num1.compareTo(num2)).isNegative();
        assertThat(num2.compareTo(num1)).isPositive();
        assertThat(num2.compareTo(num3)).isNegative();
        assertThat(num2.compareTo(num2)).isZero();
    }
}
