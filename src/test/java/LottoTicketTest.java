import domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @Nested
    @DisplayName("정상 생성")
    class Creation {

        @Test
        @DisplayName("6개, 중복없음, 1~45 범위면 생성된다")
        void createsNormally() {
            assertThatCode(() -> new LottoTicket(List.of(1, 2, 3, 4, 5, 6)))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("예외 처리")
    class Exceptions {

        @Test
        @DisplayName("번호가 6개가 아니면 예외")
        void throwsWhenSizeIsNotSix() {
            assertThatThrownBy(() -> new LottoTicket(List.of(1, 2, 3, 4, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("중복 번호가 있으면 예외")
        void throwsWhenDuplicated() {
            assertThatThrownBy(() -> new LottoTicket(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("범위를 벗어나면 예외(0 포함)")
        void throwsWhenOutOfRangeLow() {
            assertThatThrownBy(() -> new LottoTicket(List.of(0, 2, 3, 4, 5, 6)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("범위를 벗어나면 예외(46 포함)")
        void throwsWhenOutOfRangeHigh() {
            assertThatThrownBy(() -> new LottoTicket(List.of(1, 2, 3, 4, 5, 46)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
