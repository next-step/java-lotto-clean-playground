import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Nested
    @DisplayName("로또 생성 테스트")
    class create {

        @Test
        @DisplayName("서로 다른 1~45 번호 6개면 생성 성공 테스트")
        void 유효한_번호면_생성_성공() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            Lotto lotto = new Lotto(numbers);

            assertThat(lotto.getNumbers()).isEqualTo(numbers);
        }

        @Test
        @DisplayName("번호가 6개가 아니면 예외 발생 테스트")
        void 번호_개수가_6개가_아니면_예외_발생() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5);
            String throwMessage = "로또 번호는 6개여야 합니다.";

            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("번호가 1~45 범위를 벗어나면 예외 발생 테스트")
        void 번호가_범위를_벗어나면_예외_발생() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);
            String throwMessage = "로또 번호는 1부터 45 사이여야 합니다.";

            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("번호가 중복되면 예외 발생 테스트")
        void 번호가_중복되면_예외_발생() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);
            String throwMessage = "로또 번호는 중복될 수 없습니다.";

            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }
    }
}
