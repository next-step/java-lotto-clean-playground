package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    @DisplayName("로또 번호가 1부터 45까지의 범위 이내라면 객체가 생성된다.")
    void range_validation_pass_test() {
        assertDoesNotThrow(() -> new LottoNumber(1));
        assertDoesNotThrow(() -> new LottoNumber(45));
        assertDoesNotThrow(() -> new LottoNumber(23));
    }

    @Test
    @DisplayName("로또 번호가 1부터 45의 범위를 벗어나면 예외가 발생한다.")
    void range_validation_fail_test() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(-5));
    }

    @Test
    @DisplayName("로또 번호가 올바르게 정렬되어야 한다.")
    void sort_test() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(42),
                new LottoNumber(7),
                new LottoNumber(15)
        );
        /* sort()에 null을 전달하면
        Comparable 인터페이스의
        compareTo 메서드가
        사용되어 정렬됨. */
        numbers.sort(null);

        List<LottoNumber> expected = Arrays.asList(
                new LottoNumber(7),
                new LottoNumber(15),
                new LottoNumber(42)
        );

        assertEquals(expected, numbers);
    }
}