import model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    @Nested
    @DisplayName("Test #1 - 기본 기능 테스트")
    class GeneralTest {
        @Test
        @DisplayName("정상적인 값을 이용해 로또를 생성한 이후 로또 값을 불러올 경우, 예외 발생 없이 값이 반환되어야 한다.")
        void should_returnLottoValue_withoutException() {
            Lotto lotto = new Lotto(Arrays.asList(1, 4, 9, 16, 25, 45));

            assertThat(lotto.getValue()).isEqualTo(Arrays.asList(1, 4, 9, 16, 25, 45));
        }

        @Test
        @DisplayName("로또 값을 불러올 경우에는 정렬된 형태의 로또 값을 반환해야 한다.")
        void should_returnSortedLottoValue() {
            Lotto lotto = new Lotto(Arrays.asList(9, 1, 12, 41, 25, 40));

            assertThat(lotto.getValue()).isEqualTo(Arrays.asList(1, 9, 12, 25, 40, 41));
        }
    }

    @Nested
    @DisplayName("Test #2 - 유효성 검사 테스트")
    class ValidationTest {
        @Test
        @DisplayName("로또 값을 만들기 위해 주어진 수가 6개가 아닌 경우 관련 예외를 설명하는 예외를 발생시켜야 한다.")
        void should_throwException_If_LottoNumberCountIsNotSix() {
            assertThatIllegalArgumentException().isThrownBy(() -> {
                Lotto lotto = new Lotto(Arrays.asList(7, 20, 45, 19, 3));
            }).withMessage("로또 번호는 6개의 수로 이루어져야 합니다.");
        }

        @Test
        @DisplayName("로또 값을 만들기 위해 주어진 수 중 하나라도 1 이상 45 이하의 정수가 아닐 경우 관련 예외를 설명하는 예외를 발생시켜야 한다.")
        void should_throwException_If_LottoNumberRangeIncorrect() {
            assertThatIllegalArgumentException().isThrownBy(() -> {
                Lotto lotto = new Lotto(Arrays.asList(0, 1, 2, 3, 4, 46));
            }).withMessage("로또 번호는 1 이상 45 이하여야 합니다.");
        }

        @Test
        @DisplayName("로또 값을 만들기 위해 주어진 수에 중복이 발생할 경우 관련 예외를 설명하는 예외를 발생시켜야 한다.")
        void should_throwException_If_LottoNumberDuplicates() {
            assertThatIllegalArgumentException().isThrownBy(() -> {
                Lotto lotto = new Lotto(Arrays.asList(1, 40, 28, 40, 17, 34));
            }).withMessage("로또 번호는 중복되어서는 안 됩니다.");
        }
    }
}
