package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("당첨번호 5개와 보너스 번호가 일치하면 2등이다")
    void 당첨번호_5개와_보너스_번호가_일치하면_2등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(40));

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 40));

        Rank rank = Rank.findRank(lotto, winningNumbers);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("당첨번호 5개가 일치하고 보너스 번호가 다르면 3등이다")
    void 당첨번호_5개가_일치하고_보너스_번호가_다르면_3등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(40));

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = Rank.findRank(lotto, winningNumbers);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }
}
