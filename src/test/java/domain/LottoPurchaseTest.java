package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoPurchaseTest {
    private LottoPurchase lottoPurchase;

    @BeforeEach
    void setUp() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(3500);
        lottoPurchase = new LottoPurchase(purchaseAmount, new RandomLottoGenerator());
    }

    @Test
    void 구매_금액을_로또_가격으로_나눈_몫만큼_로또를_발급한다() {
        List<Lotto> lottos = lottoPurchase.issueLottos();

        assertThat(lottos).hasSize(3);
    }

    @Test
    void 수동_구매_수만큼_제외하고_자동_로또를_발급한다() {
        List<Lotto> autoLottos = lottoPurchase.issueRemainingAutoLottos(2);

        assertThat(autoLottos).hasSize(1);
    }

    @Test
    void 수동_구매_수가_전체_구매_수와_같으면_자동_로또를_발급하지_않는다() {
        List<Lotto> autoLottos = lottoPurchase.issueRemainingAutoLottos(3);

        assertThat(autoLottos).isEmpty();
    }

    @Test
    void 수동_구매_수가_전체_구매_수보다_크면_예외를_던진다() {
        assertThatThrownBy(() -> lottoPurchase.issueRemainingAutoLottos(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수동_구매_수가_음수면_예외를_던진다() {
        assertThatThrownBy(() -> lottoPurchase.issueRemainingAutoLottos(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
