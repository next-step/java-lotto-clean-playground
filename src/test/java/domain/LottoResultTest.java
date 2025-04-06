package domain;

import enums.LottoRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    LottoResult lottoResult;
    List<Lotto> lottos;
    LottoPurchase lottoPurchase;

    @BeforeEach
    void beforeEach() {
        Lotto lotto1 = Lotto.from(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        Lotto lotto2 = Lotto.from(new ArrayList<>(List.of(2, 3, 4, 5, 6, 7)));
        Lotto lotto3 = Lotto.from(new ArrayList<>(List.of(3, 4, 5, 6, 7, 8)));

        lottos = new ArrayList<>();
        lottos.add(lotto1);
        lottos.add(lotto2);
        lottos.add(lotto3);

        lottoPurchase = LottoPurchase.createLottoPurchase(Money.from(5000), LottoCount.from(2), lottos);
    }

    @Test
    @DisplayName("lottoResult가_로또의_맞춘숫자를_정확히_계산한다")
    void lottoResult가_로또의_맞춘숫자를_정확히_계산한다() {

        //given
        LottoWinningNumbers winningNumbers = LottoWinningNumbers.from(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoResult = LottoResult.createLottoResult(winningNumbers, lottoPurchase);

        //when
        Map<LottoRank, Integer> resultByRank = lottoResult.getResultByRank();

        //then
        assertThat(resultByRank.get(LottoRank.NO_MATCH)).isEqualTo(0);
        assertThat(resultByRank.get(LottoRank.MATCH_3)).isEqualTo(0);
        assertThat(resultByRank.get(LottoRank.MATCH_4)).isEqualTo(1);
        assertThat(resultByRank.get(LottoRank.MATCH_5)).isEqualTo(0);
        assertThat(resultByRank.get(LottoRank.MATCH_5_BONUS)).isEqualTo(1);
        assertThat(resultByRank.get(LottoRank.MATCH_6)).isEqualTo(1);
    }

    @Test
    @DisplayName("lottoResult가_상금수익률을_정확히_계산한다")
    void lottoResult가_상금수익률을_정확히_계산한다() {
        //given
        LottoWinningNumbers winningNumbers = LottoWinningNumbers.from(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoResult = LottoResult.createLottoResult(winningNumbers, lottoPurchase);

        //when
        Double profitRate = lottoResult.getProfitRate();

        //then
        assertThat(profitRate).isEqualTo(406010);
    }

    @Test
    @DisplayName("lottoResult가 상금을 정확히 계산한다")
    void lottoResult가_상금을_정확히_계산한다() {
        //given
        LottoWinningNumbers winningNumbers = LottoWinningNumbers.from(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoResult = LottoResult.createLottoResult(winningNumbers, lottoPurchase);

        //when
        double amount = lottoResult.calculatePrize().getAmount();

        //then
        assertThat(amount).isEqualTo(2_030_050_000.0);
    }
}
