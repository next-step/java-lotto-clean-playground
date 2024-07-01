package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;
import org.duckstudy.model.lotto.constant.WinningRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 당첨 통계 테스트")
class LottoStatisticsTest {

    @Test
    @DisplayName("로또 당첨 통계를 생성한다")
    void createLottoStatistics() {

        Lotto lotto = Lotto.from(Set.of(1, 2, 3, 4, 5, 7));
        Lotto winningLotto = Lotto.from(Set.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        LottoStatistics lottoStatistics = LottoStatistics.createLottoResult(lotto, winningLotto, bonusNumber);

        assertThat(lottoStatistics.getStatistics())
                .containsEntry(WinningRank.SECOND, 1);
    }

    @Test
    @DisplayName("로또 당첨 통계를 병합한다")
    void mergeLottoStatistics() {

        LottoStatistics lottoStatistics1 = new LottoStatistics(Map.of(WinningRank.FIFTH, 2));
        LottoStatistics lottoStatistics2 = new LottoStatistics(Map.of(WinningRank.FIFTH, 1));

        LottoStatistics mergedLottoStatistics = lottoStatistics1.merge(lottoStatistics2);

        assertThat(mergedLottoStatistics.getStatistics())
                .containsEntry(WinningRank.FIFTH, 3);
    }
}
