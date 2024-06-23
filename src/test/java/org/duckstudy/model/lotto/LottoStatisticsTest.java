package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;
import org.duckstudy.model.lotto.constant.WinningRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 당첨 결과 테스트")
class LottoStatisticsTest {

    @Test
    @DisplayName("로또 결과를 생성한다")
    void createLottoResult() {

        Lotto lotto = Lotto.from(Set.of(1, 2, 3, 4, 5, 7));
        Lotto winningLotto = Lotto.from(Set.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        LottoStatistics lottoStatistics = LottoStatistics.createLottoResult(lotto, winningLotto, bonusNumber);

        assertThat(lottoStatistics.getStatistics())
                .containsEntry(WinningRank.SECOND.getKey(), 1);
    }

    @Test
    @DisplayName("로또 결과를 병합한다")
    void mergeLottoResult() {

        LottoStatistics lottoStatistics1 = new LottoStatistics(Map.of(3, 2));
        LottoStatistics lottoStatistics2 = new LottoStatistics(Map.of(3, 1));

        LottoStatistics mergedLottoStatistics = lottoStatistics1.merge(lottoStatistics2);

        assertThat(mergedLottoStatistics.getStatistics())
                .containsEntry(3, 3);
    }
}
