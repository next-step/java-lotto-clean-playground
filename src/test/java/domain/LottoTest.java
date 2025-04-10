package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Nested
    class testLottoOf {
        @Test
        @DisplayName("로또 번호가 6개가 아니면 예외가 발생해야 한다.")
        void testLottoNumberCountException() {
            List<Integer> testLottoNumbers = Arrays.asList(1, 2, 3, 4, 5);

            assertThatThrownBy(() -> Lotto.of(testLottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("로또 번호에 중복이 존재할 경우 예외가 발생해야 한다.")
        void testLottoNumberDuplicateException() {
            List<Integer> testLottoNumbers = Arrays.asList(1, 1, 2, 3, 4, 5);

            assertThatThrownBy(() -> Lotto.of(testLottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
        }

    }



    @Test
    @DisplayName("두 로또 간 일치하는 번호의 개수를 반환해야 한다.")
    void testMatchNumbers() {
        Lotto lotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.of(Arrays.asList(3, 4, 5, 6, 7, 8));
        int expected = 4;

        int actual = lotto.matchNumbers(winningLotto);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또에 보너스 번호가 포함되어있으면 참을 반환해야한다")
    void testHasBonusNumber() {
        Lotto lotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.from(3);
        boolean expected = true;

        boolean actual = lotto.hasBonusNumber(bonusNumber);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또에 보너스 번호가 포함되어있지 않다면 거짓을 반환해야한다")
    void testHasNotBonusNumber() {
        Lotto lotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.from(7);
        boolean expected = false;

        boolean actual = lotto.hasBonusNumber(bonusNumber);

        assertThat(actual).isEqualTo(expected);
    }

}
