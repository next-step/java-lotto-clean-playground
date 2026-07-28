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
    void 구입_금액을_로또_가격으로_나누어_구매_수량을_계산한다() {
        assertThat(lottoPurchase.getLottoCount()).isEqualTo(3);
    }

    @Test
    void 수동_구매_수가_0이면_전체_구매_수만큼_자동_로또를_발급한다() {
        // given
        List<List<Integer>> manualLottoNumbers = List.of();

        // when
        PurchasedLottos purchasedLottos = lottoPurchase.purchase(manualLottoNumbers);

        // then
        assertThat(purchasedLottos.lottos()).hasSize(3);
        assertThat(purchasedLottos.manualLottoCount()).isEqualTo(0);
        assertThat(purchasedLottos.autoLottoCount()).isEqualTo(3);
    }

    @Test
    void 수동_구매_수만큼_제외하고_자동_로또를_발급한다() {
        // given
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );

        // when
        PurchasedLottos purchasedLottos = lottoPurchase.purchase(manualLottoNumbers);

        // then
        assertThat(purchasedLottos.lottos()).hasSize(3);
        assertThat(purchasedLottos.manualLottoCount()).isEqualTo(2);
        assertThat(purchasedLottos.autoLottoCount()).isEqualTo(1);
    }

    @Test
    void 수동_구매_수가_전체_구매_수와_같으면_자동_로또를_발급하지_않는다() {
        // given
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(13, 14, 15, 16, 17, 18)
        );

        // when
        PurchasedLottos purchasedLottos = lottoPurchase.purchase(manualLottoNumbers);

        // then
        assertThat(purchasedLottos.lottos()).hasSize(3);
        assertThat(purchasedLottos.manualLottoCount()).isEqualTo(3);
        assertThat(purchasedLottos.autoLottoCount()).isEqualTo(0);
    }

    @Test
    void 수동_구매_수가_전체_구매_수보다_크면_예외를_던진다() {
        // given
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(13, 14, 15, 16, 17, 18),
                List.of(19, 20, 21, 22, 23, 24)
        );

        // when & then
        assertThatThrownBy(() -> lottoPurchase.purchase(manualLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
