package domain.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("당첨 번호와 0개 일치하면 일치 개수는 0이다.")
    void returnZeroWhenZeroNumberMatch() {
        // given
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.from(List.of(7, 8, 9, 10, 11, 12));

        // when
        int count = lotto.countMatches(winningLotto);

        // then
        assertEquals(0, count);
    }

    @Test
    @DisplayName("당첨 번호와 3개 일치하면 일치 개수는 3이다.")
    void returnThreeWhenThreeNumberMatch() {
        // given
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.from(List.of(1, 3, 5, 7, 9, 11));

        // when
        int count = lotto.countMatches(winningLotto);

        // then
        assertEquals(3, count);
    }

    @Test
    @DisplayName("당첨 번호와 6개 일치하면 일치 개수는 6이다.")
    void returnSixWhenSixNumberMatch() {
        // given
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        // when
        int count = lotto.countMatches(winningLotto);

        // then
        assertEquals(6, count);
    }
}
