import domain.Lotto;
import domain.Rank;
import domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {

    @Nested
    @DisplayName("당첨 번호 생성 테스트")
    class from {

        @Test
        @DisplayName("쉼표로 구분된 숫자 문자열로 생성 성공 테스트")
        void 문자열로_생성_성공() {
            WinningNumbers winningNumbers = WinningNumbers.from(List.of("1", "2", "3", "4", "5", "6"));

            assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("공백이 섞여도 정렬해서 생성 테스트")
        void 공백_섞여도_정렬() {
            WinningNumbers winningNumbers = WinningNumbers.from(List.of(" 6", "3 ", " 1 ", "5", "2", "4"));

            assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("숫자가 아니면 예외 발생 테스트")
        void 숫자가_아니면_예외() {
            String throwMessage = "당첨 번호는 숫자로 입력해야 합니다.";

            assertThatThrownBy(() -> WinningNumbers.from(List.of("1", "2", "3", "4", "5", "a")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("6개가 아니면 예외 발생 테스트")
        void 개수가_6개가_아니면_예외() {
            String throwMessage = "당첨 번호는 6개여야 합니다.";

            assertThatThrownBy(() -> WinningNumbers.from(List.of("1", "2", "3", "4", "5", "6", "7")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }
    }

    @Nested
    @DisplayName("당첨 등수 판정 테스트")
    class match {

        private final WinningNumbers winningNumbers =
                new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        @Test
        @DisplayName("당첨 번호와 겹친 개수만큼 등수 판정 테스트")
        void 겹친_개수로_등수_판정() {
            Rank expectedRank = Rank.THREE;
            Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

            assertThat(winningNumbers.match(lotto)).isEqualTo(expectedRank);
        }
    }
}
