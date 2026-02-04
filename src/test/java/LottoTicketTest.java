import domain.Lotto;
import domain.LottoNumber;
import domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    private LottoTicket ticket(int... nums) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int n : nums) numbers.add(LottoNumber.of(n));
        return new LottoTicket(new Lotto(numbers));
    }

    @Nested
    @DisplayName("보너스 포함 여부")
    class Bonus {

        @Test
        @DisplayName("보너스 번호가 포함되면 true")
        void hasBonusTrue() {
            LottoTicket t = ticket(1, 2, 3, 4, 5, 7);
            assertThat(t.hasBonus(7)).isTrue();
        }

        @Test
        @DisplayName("보너스 번호가 없으면 false")
        void hasBonusFalse() {
            LottoTicket t = ticket(1, 2, 3, 4, 5, 7);
            assertThat(t.hasBonus(8)).isFalse();
        }
    }

    @Nested
    @DisplayName("출력")
    class Output {

        @Test
        @DisplayName("toString은 오름차순으로 출력한다")
        void toStringPrintsSorted() {
            LottoTicket t = ticket(6, 1, 4, 2, 5, 3);
            assertThat(t.numbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }
    }
}
