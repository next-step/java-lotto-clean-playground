package controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import model.Lotto;
import model.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ControllerTest {

    @Test
    @DisplayName("당첨_금액의_총합을_계산한다")
    void 당첨_금액의_총합을_계산한다() {
        EnumMap<Rank, Integer> counts = new EnumMap<>(Rank.class);
        counts.put(Rank.FIFTH, 2);
        counts.put(Rank.FOURTH, 1);
        counts.put(Rank.THIRD, 0);
        counts.put(Rank.SECOND, 0);
        counts.put(Rank.FIRST, 0);

        Result result = new Result(counts);

        assertThat(result.totalPrize()).isEqualTo(60_000);
    }

    @Test
    @DisplayName("당첨_금액_수익률계산")
    void 당첨_금액_수익률계산() {
        int cost=60_000;
        EnumMap<Rank, Integer> counts = new EnumMap<>(Rank.class);
        counts.put(Rank.FIFTH, 2); //10,000
        counts.put(Rank.FOURTH, 1); // 50,000
        counts.put(Rank.THIRD, 0);
        counts.put(Rank.SECOND, 0);
        counts.put(Rank.FIRST, 0);

        Result result = new Result(counts);

        float ratio=result.totalRatio(cost);

        assertThat(ratio).isEqualTo(1.0f);
    }


    @Test
    @DisplayName("당첨_번호와_구매한_로또가_5개_일치했지만보너스_번호가_일치하지_않을_때_3등이_되는지")
    void 당첨_번호와_구매한_로또가_5개_일치했지만보너스_번호가_일치하지_않을_때_3등이_되는지() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 40));

        Rank rank = winningLotto.findRank(purchasedLotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName(("당첨_번호와_구매한_로또가_5개_일치하고_보너스_번호도_일치할때_2등이_되는지"))
    void 당첨_번호와_구매한_로또가_5개_일치하고_보너스_번호도_일치할때_2등이_되는지() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = winningLotto.findRank(purchasedLotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName(("우승번호가_보너스볼을_포함할때"))
    void 우승번호가_보너스볼을_포함할때() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        })
            .isInstanceOf(IllegalArgumentException.class);

    }
}

