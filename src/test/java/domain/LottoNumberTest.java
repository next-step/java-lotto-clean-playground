package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void acceptsBoundaryNumbers(int number) {
        // 실행 및 검증
        assertThat(new LottoNumber(number).getNumber()).isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void rejectsOutOfRangeNumbers(int number) {
        // 실행 및 검증
        assertThatThrownBy(() -> new LottoNumber(number)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void comparesNumbersByValue() {
        // 준비
        LottoNumber first = new LottoNumber(7);
        LottoNumber second = new LottoNumber(7);
        // 검증
        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
        assertThat(first).isNotEqualTo(new LottoNumber(8));
    }
}
