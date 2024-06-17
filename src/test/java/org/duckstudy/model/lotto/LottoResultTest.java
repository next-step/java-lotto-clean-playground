package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.duckstudy.model.lotto.constant.WinningRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 당첨 결과 테스트")
class LottoResultTest {

    @Test
    @DisplayName("로또 결과를 생성한다")
    void createLottoResult() {

        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        LottoResult lottoResult = LottoResult.createLottoResult(lotto, winningLotto, bonusNumber);

        assertThat(lottoResult.getResult())
                .containsEntry(WinningRank.SECOND.getKey(), 1);
    }

    @Test
    @DisplayName("로또 결과를 병합한다")
    void mergeLottoResult() {

        LottoResult lottoResult1 = new LottoResult(Map.of(3, 2));
        LottoResult lottoResult2 = new LottoResult(Map.of(3, 1));

        LottoResult mergedLottoResult = lottoResult1.merge(lottoResult2);

        assertThat(mergedLottoResult.getResult())
                .containsEntry(3, 3);
    }
}
