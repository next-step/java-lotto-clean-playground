package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;

import domain.purchase.PurchasePrice;
import domain.result.LottoResult;
import domain.result.MatchResult;
import domain.result.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoResultTest {

    @Test
    void 등급별_당첨_개수를_계산한다() {
        // given
        List<MatchResult> matchResults = List.of(
                new MatchResult(6, false),
                new MatchResult(5, true),
                new MatchResult(5, false),
                new MatchResult(4, false),
                new MatchResult(3, false),
                new MatchResult(3, false)
        );

        // when
        LottoResult result = new LottoResult(matchResults);

        // then
        assertThat(result.getResults())
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 1)
                .containsEntry(Rank.FOURTH, 1)
                .containsEntry(Rank.FIFTH, 2);
    }

    @Test
    void 세개_미만_일치는_당첨결과에_포함되지_않는다() {
        // when
        List<MatchResult> matchResults = List.of(
                new MatchResult(2, false),
                new MatchResult(1, false),
                new MatchResult(0, false)
        );

        // when
        LottoResult result = new LottoResult(matchResults);

        // then
        assertThat(result.getResults())
                .containsEntry(Rank.FIRST, 0)
                .containsEntry(Rank.SECOND, 0)
                .containsEntry(Rank.THIRD, 0)
                .containsEntry(Rank.FOURTH, 0)
                .containsEntry(Rank.FIFTH, 0);
    }

    @Test
    void 당첨금과_구입금액으로_수익률을_계산한다() {
        // given
        List<MatchResult> matchResults = List.of(
                new MatchResult(3, false)
        );
        LottoResult result = new LottoResult(matchResults);
        PurchasePrice purchasePrice = new PurchasePrice(14000);

        // when
        double rate = result.calculateRateOfReturn(purchasePrice);

        // then
        assertThat(rate)
                .isCloseTo(0.3571428571, within(0.0000001));
    }
}
