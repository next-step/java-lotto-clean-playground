package domain.purchase;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @Test
    @DisplayName("구매한 로또마다 당첨 번호와의 일치 개수를 순서대로 반환한다")
    void countMatchesInOrder() {
        // given
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto3 = Lotto.from(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        // when
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> matches = lottos.countMatches(winningLotto);

        // then
        assertThat(matches).containsExactly(6,3,0);
    }
}
