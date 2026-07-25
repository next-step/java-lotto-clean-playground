import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.Rank;
import domain.WinningLotto;
import domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class LottoResultTest {

    private final WinningLotto winningLotto = new WinningLotto(
            new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)),
            new LottoNumber(7));

    @Test
    @DisplayName("로또 등수 개수 집계 테스트")
    void 등수별_개수_집계() {
        Rank expectedRank = Rank.FIFTH;
        int expectedCount = 2;
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),    // 3개 일치 → FIFTH
                new Lotto(List.of(1, 2, 3, 20, 21, 22)),    // 3개 일치 → FIFTH
                new Lotto(List.of(30, 31, 32, 33, 34, 35))  // MISS
        ));

        LottoResult result = new LottoResult(lottos, winningLotto);

        assertThat(result.countOf(expectedRank)).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("수익률 테스트")
    void 수익률_계산() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)) // 3개 일치 → FIFTH (5000원)
        ));

        LottoResult result = new LottoResult(lottos, winningLotto);

        assertThat(result.profitRate(new Money(14000))).isCloseTo(5000.0 / 14000, within(0.0001));
    }
}
