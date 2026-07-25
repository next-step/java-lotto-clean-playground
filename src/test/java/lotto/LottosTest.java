package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    @DisplayName("구매한 로또들의 당첨 결과를 등수별로 계산한다")
    void createResult() {
        Lotto winningLotto = lotto(1, 2, 3, 4, 5, 6);
        Lottos purchasedLottos = new Lottos(Arrays.asList(
                lotto(1, 2, 3, 10, 11, 12),
                lotto(1, 2, 3, 4, 11, 12),
                lotto(10, 11, 12, 13, 14, 15)));

        LottoResult result = purchasedLottos.createResult(winningLotto);

        assertThat(result.countOf(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.THIRD)).isEqualTo(1);
        assertThat(result.countOf(LottoRank.MISS)).isEqualTo(1);
        assertThat(result.totalPrizeMoney()).isEqualTo(new PrizeMoney(55_000));
        assertThat(result.calculateProfitRate(new PurchaseAmount(110_000)))
                .isEqualTo(new ProfitRate(0.5));
    }

    private Lotto lotto(int first, int second, int third, int fourth, int fifth, int sixth) {
        return new Lotto(Arrays.asList(
                number(first), number(second), number(third),
                number(fourth), number(fifth), number(sixth)));
    }

    private LottoNumber number(int value) {
        return new LottoNumber(value);
    }
}
