package domain;

import domain.lotto.Lotto;
import domain.lotto.wrap.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static helper.TestHelperMethod.toLottoNumbers;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호가 7개면 예외 발생")
    void ifSevenNumbers() {
        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                () -> new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @Test
    @DisplayName("로또 번호가 5개면 예외 발생")
    void ifFiveNumbers() {
        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                () -> new Lotto(toLottoNumbers(1, 2, 3, 4, 5))
        );
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외 발생")
    void ifDuplicateNumbers() {
        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                () -> new Lotto(toLottoNumbers(1, 1, 2, 3, 4, 5))
        );
    }

    @Test
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외 발생")
    void ifOutOfRange() {
        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                () -> new LottoNumber(46)
        );
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(0)
        );
    }
}
