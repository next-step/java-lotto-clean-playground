package model.lotto;

import model.BonusBall;
import model.LottoNumbersGenerator;
import model.Ranking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    private Lotto winningNumbers = ManualLotto.of(List.of(1,2,3,4,5,6));
    private BonusBall bonusBall = BonusBall.of(7, winningNumbers);

    @Test
    void getLotto를_통해_얻은_객체를_외부에서_변경해도_원상태는_유지되어야_한다() {
        winningNumbers.getNumbers().add(10);
        assertThat(winningNumbers.getNumbers().size()).isEqualTo(6);
    }

    @Test
    void 로또번호가_우승번호와_모두_일치하면_FIRST를_반환해야_한다() {
        Lotto lotto = ManualLotto.of(List.of(1, 2, 3, 4, 5, 6));
        Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
        assertThat(ranking).isEqualTo(Ranking.FIRST);
    }

    @Test
    void 로또번호가_우승번호와_5개_일치하고_보너스볼이_일치하면_SECOND를_반환해야_한다() {
        Lotto lotto = ManualLotto.of(List.of(1, 2, 3, 4, 5, 7));
        Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
        assertThat(ranking).isEqualTo(Ranking.SECOND);
    }

    @Test
    void 로또번호가_우승번호와_5개_일치하면_THIRD를_반환해야_한다() {
        Lotto lotto = ManualLotto.of(List.of(1, 2, 3, 4, 5, 10));
        Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
        assertThat(ranking).isEqualTo(Ranking.THIRD);
    }

    @Test
    void 로또번호가_우승번호와_4개_일치하면_FIRST를_반환해야_한다() {
        Lotto lotto = ManualLotto.of(List.of(1, 2, 3, 4, 8, 9));
        Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
        assertThat(ranking).isEqualTo(Ranking.FOURTH);
    }

    @Test
    void 로또번호가_우승번호와_3개_일치하면_FIFTH를_반환해야_한다() {
        Lotto lotto = ManualLotto.of(List.of(1, 2, 3, 9, 10, 11));
        Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
        assertThat(ranking).isEqualTo(Ranking.FIFTH);
    }

    @Test
    void 로또번호가_우승번호와_2개_이하로_일치하면_MISS를_반환해야_한다() {
        Lotto lotto = ManualLotto.of(List.of(10, 20, 30, 40, 5, 6));
        Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
        assertThat(ranking).isEqualTo(Ranking.MISS);
    }
}
