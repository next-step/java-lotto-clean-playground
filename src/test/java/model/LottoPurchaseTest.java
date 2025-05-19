package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseTest {

    private static Lotto createDummyLotto() {
        return new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
    }

    @Test
    @DisplayName("구매 금액이 음수면 예외 발생")
    void negativePurchaseAmount() {
        assertThatThrownBy(() -> new LottoPurchase(List.of(), -1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("purchaseAmount은 0보다 큰 값이여야 합니다.");
    }

    @Test
    @DisplayName("수동 로또 금액이 구매 금액을 초과하면 예외 발생")
    void manualLottoCostExceedsPurchaseAmount() {
        List<Lotto> manualLottos = List.of(createDummyLotto(), createDummyLotto()); // 2장 → 2000원
        int purchaseAmount = 1000;

        assertThatThrownBy(() -> new LottoPurchase(manualLottos, purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력한 수동 로또 구입 수가 구매 가능한 로또 수 보다 큽니다.");
    }

    @Test
    @DisplayName("자동 로또 개수는 (총 구매 가능 장수 - 수동 장수)로 계산된다")
    void getAutoLottoCount() {
        List<Lotto> manualLottos = List.of(createDummyLotto(), createDummyLotto()); // 2장
        int purchaseAmount = 5000; // 5장 구매 가능
        LottoPurchase purchase = new LottoPurchase(manualLottos, purchaseAmount);

        assertThat(purchase.getAutoLottoCount()).isEqualTo(3);
    }
}
