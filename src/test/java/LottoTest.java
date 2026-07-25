import domain.Lotto;
import domain.LottoNumber;
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
            List<Integer> expectedLotto = List.of(1, 2, 3, 4, 5, 6);

            Lotto lotto = Lotto.from(List.of("1", "2", "3", "4", "5", "6"));

            assertThat(lotto.getNumbers())
                    .extracting(LottoNumber::getValue)
                    .containsExactlyElementsOf(expectedLotto);
        }

        @Test
        @DisplayName("번호가 6개가 아니면 예외 발생 테스트")
        void 번호_개수가_6개가_아니면_예외_발생() {
            String throwMessage = "로또 번호는 6개여야 합니다.";

            assertThatThrownBy(() -> Lotto.from(List.of("1", "2", "3", "4", "5")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("번호가 1~45 범위를 벗어나면 예외 발생 테스트")
        void 번호가_범위를_벗어나면_예외_발생() {
            String throwMessage = "로또 번호는 1부터 45 사이여야 합니다.";

            assertThatThrownBy(() -> Lotto.from(List.of("1", "2", "3", "4", "5", "46")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("번호가 중복되면 예외 발생 테스트")
        void 번호가_중복되면_예외_발생() {
            String throwMessage = "로또 번호는 중복될 수 없습니다.";

            assertThatThrownBy(() -> Lotto.from(List.of("1", "2", "3", "4", "5", "5")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("숫자가 아니면 예외 발생 테스트")
        void 숫자가_아니면_예외_발생() {
            String throwMessage = "로또 번호는 숫자로 입력해야 합니다.";

            assertThatThrownBy(() -> Lotto.from(List.of("1", "2", "3", "4", "5", "a")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }
    }
}
