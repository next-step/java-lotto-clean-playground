package domain;

import factory.LottoGeneratorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoPurchaseTest {

    LottoGeneratorFactory generatorFactory;

    @BeforeEach
    void setUp() {
        generatorFactory = new LottoGeneratorFactory();
    }

    @Test
    @DisplayName("로또 금액이 유효한 값일 때, 구매한 로또 개수가 계산되어야 한다.")
    void create_LottoPurchase_WithValidAmount() {
        long purchaseAmount = 5000;
        List<Lotto> manualLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList())
        );

        LottoPurchase lottoPurchase = new LottoPurchase(purchaseAmount, manualLottos, generatorFactory);

        assertEquals(5, lottoPurchase.getLottoList().size()); // 총 5개여야 함
    }

    @Test
    @DisplayName("구매 개수에서 수동 구매 개수를 뺀만큼 자동 로또 개수가 결정되어야 한다.")
    void autoLottoCount_ShouldDecrease_WhenManualLottosAreGiven() {
        long purchaseAmount = 5000;
        List<Lotto> manualLottos = List.of( //수동 로또 2개 구매
                new Lotto(List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList()),
                new Lotto(List.of(7, 8, 9, 10, 11, 12).stream().map(LottoNumber::new).toList())
        );

        LottoPurchase lottoPurchase = new LottoPurchase(purchaseAmount, manualLottos, generatorFactory);

        assertEquals(3, lottoPurchase.getLottoList().stream().filter(l -> !manualLottos.contains(l)).count()); // 자동은 2개여야 함
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 미만이면 예외가 발생한다.")
    void validate_PurchaseAmount_BelowMinimum_ThrowsException() {
        long purchaseAmount = 500;

        assertThatThrownBy(() -> new LottoPurchase(purchaseAmount, List.of(), generatorFactory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 최소 구매 금액은 1000원입니다.");
    }

    @Test
    @DisplayName("로또 금액이 1000원 단위가 아니라면 예외가 발생한다.")
    void validate_PurchaseAmount_NotMultipleOfThousand_ThrowsException() {
        long purchaseAmount = 1500;

        assertThatThrownBy(() -> new LottoPurchase(purchaseAmount, List.of(), generatorFactory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 금액은 1000원 단위여야 합니다.");
    }
}
