import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    private void ticket(int... nums) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int n : nums) {
            numbers.add(LottoNumber.of(n));
        }
        new Lotto(numbers);
    }

    @Nested
    @DisplayName("생성")
    class Creation {

        @Test
        @DisplayName("6개면 생성된다")
        void createsNormally() {
            assertThatCode(() -> ticket(1, 2, 3, 4, 5, 6))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("6개가 아니면 예외")
        void throwsWhenSizeNotSix() {
            assertThatThrownBy(() -> ticket(1, 2, 3, 4, 5))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("중복이 있으면 예외")
        void throwsWhenDuplicated() {
            assertThatThrownBy(() -> ticket(1, 2, 3, 4, 5, 5))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 중복될 수 없습니다.");
        }
    }
}
