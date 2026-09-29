package domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import domain.purchase.Lotto;
import domain.purchase.Lottos;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    @Test
    @DisplayName("구매한 로또들과 비교해 등수별 당첨 결과를 반환한다.")
    void matchReturnResultByRank() {
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto3 = Lotto.from(List.of(7, 8, 9, 10, 11, 12));

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        Lotto winningNumbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        WinningLotto winningLotto = new WinningLotto(winningNumbers);

        LottoResult result = winningLotto.match(lottos);

        assertThat(result.countOf(LottoRank.MISS)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.THREE)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.SIX)).isEqualTo(1);
    }
}
