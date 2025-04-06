package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoPurchaseTest {

    Money money;
    LottoCount lottoCount;
    List<Lotto> lottos;

    @BeforeEach
    void beforeEach() {
        money = Money.from(5000);
        lottoCount = LottoCount.from(3);
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(2, 3, 4, 5, 6, 7));
        Lotto lotto3 = Lotto.from(List.of(3, 4, 5, 6, 7, 8));
        lottos = new ArrayList<>();
        lottos.add(lotto1);
        lottos.add(lotto2);
        lottos.add(lotto3);
    }

    @Test
    @DisplayName("LottoPurchase가 구입한 수동 로또 수를 반환한다")
    void LottoPurchase가_구입한_수동_로또_수를_반환한다() {
        //given
        LottoPurchase lottoPurchase = LottoPurchase.createLottoPurchase(money, LottoCount.from(3), lottos);

        //when
        LottoCount manualCount = lottoPurchase.getManualCount();

        //then
        assertThat(manualCount.getLottoCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("LottoPurchase가 구입한 자동 로또 수를 반환한다")
    void LottoPurchase가_구입한_자동_로또_수를_반환한다() {
        //given
        LottoPurchase lottoPurchase = LottoPurchase.createLottoPurchase(money, LottoCount.from(3), lottos);

        //when
        LottoCount autoCount = lottoPurchase.getAutoCount();

        //then
        assertThat(autoCount.getLottoCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("LottoPurchase가 구입한 로또들을 반환한다")
    void LottoPurchase가_구입한_로또들을_반환한다() {
        //given
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(2, 3, 4, 5, 6, 7));
        Lotto lotto3 = Lotto.from(List.of(3, 4, 5, 6, 7, 8));
        lottos = new ArrayList<>();
        lottos.add(lotto1);
        lottos.add(lotto2);
        lottos.add(lotto3);
        LottoPurchase lottoPurchase = LottoPurchase.createLottoPurchase(Money.from(5000), LottoCount.from(3), lottos);

        //when
        List<Lotto> getLottos = lottoPurchase.getLottos();

        //then
        assertThat(getLottos).containsExactly(lotto1, lotto2, lotto3);
    }
}