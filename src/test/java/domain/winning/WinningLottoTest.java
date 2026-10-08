package domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.purchase.Lotto;
import domain.purchase.LottoNumber;
import domain.purchase.Lottos;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    private static final Lotto WINNING_NUMBERS = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
    private static final LottoNumber BONUS_NUMBER = new LottoNumber(7);

    @Test
    @DisplayName("보너스 볼이 당첨 번호와 겹치면 오류가 발생한다.")
    void errorWhenBonusNumberDuplicated() {
        // given
        LottoNumber duplicatedBonus = new LottoNumber(6);

        // when & then
        assertThatThrownBy(() -> new WinningLotto(WINNING_NUMBERS, duplicatedBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼이 일치하면 2등이다.")
    void secondRankWhenFiveMatchesWithBonus() {
        // given
        WinningLotto winningLotto = new WinningLotto(WINNING_NUMBERS, BONUS_NUMBER);
        Lotto purchasedLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 7));

        // when
        LottoRank rank = winningLotto.rankOf(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼이 일치하지 않으면 3등이다.")
    void thirdRankWhenFiveMatchesWithoutBonus() {
        // given
        WinningLotto winningLotto = new WinningLotto(WINNING_NUMBERS, BONUS_NUMBER);
        Lotto purchasedLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 8));

        // when
        LottoRank rank = winningLotto.rankOf(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("구매한 로또들과 비교해 등수별 당첨 결과를 반환한다.")
    void matchReturnsResultByRank() {
        // given
        WinningLotto winningLotto = new WinningLotto(WINNING_NUMBERS, BONUS_NUMBER);
        Lottos lottos = new Lottos(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 8, 9, 10)),
                Lotto.from(List.of(8, 9, 10, 11, 12, 13))
        ));

        // when
        LottoResult result = winningLotto.match(lottos);

        // then
        assertThat(result.countOf(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.MISS)).isEqualTo(1);
    }
}
