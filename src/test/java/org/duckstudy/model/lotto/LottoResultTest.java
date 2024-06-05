package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.duckstudy.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 당첨 결과 테스트")
class LottoResultTest {
    @Test
    @DisplayName("로또 수익률을 계산한다")
    void calculateProfitRate() {
        LottoResult lottoResult = new LottoResult(Map.of(3, 2));

        assertThat(lottoResult.calculateProfitRate(new Price(100000))).isEqualTo(10.0);
    }

    @Test
    @DisplayName("로또 당첨 결과를 추가한다")
    void addLottoResult() {
        LottoResult lottoResult = new LottoResult(Map.of());
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 7);
        LottoNumber bonusNumber = LottoNumber.valueOf(6);

        lottoResult = lottoResult.addLottoResult(lotto, winningLotto, bonusNumber);

        assertThat(lottoResult.getResult()).containsEntry(LottoMatch.MATCH_5_WITH_BONUS.getKey(), 1);
    }
}
