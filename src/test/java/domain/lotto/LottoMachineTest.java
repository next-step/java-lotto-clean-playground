package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.money.PurchaseAmount;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("구입 금액에 맞는 개수의 로또를 생성한다")
    void createLottoByPurchaseAmount() {
        LottoMachine lottoMachine = new LottoMachine();

        PurchasedLottos purchasedLottoTickets = lottoMachine.buy(
                PurchaseAmount.from(14_000),
                PurchasedLottos.empty()
        );

        assertThat(purchasedLottoTickets.values()).hasSize(14);
    }

    @Test
    @DisplayName("수동 구매 로또와 자동 구매 로또를 함께 생성한다")
    void createManualAndAutomaticLottos() {
        LottoMachine lottoMachine = new LottoMachine();
        PurchasedLottos manualLottos = createManualLottos();

        PurchasedLottos purchasedLottoTickets = lottoMachine.buy(PurchaseAmount.from(3_000), manualLottos);

        assertThat(purchasedLottoTickets.values()).hasSize(3);
        assertThat(purchasedLottoTickets.values().get(0).values()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    private PurchasedLottos createManualLottos() {
        return new PurchasedLottos(List.of(new LottoTicket(List.of(1, 2, 3, 4, 5, 6))));
    }

    @Test
    @DisplayName("구입 금액이 0원 이하면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotPositive() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.buy(PurchaseAmount.from(0), PurchasedLottos.empty()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 0원보다 커야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotDivisibleByThousand() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.buy(PurchaseAmount.from(9_500), PurchasedLottos.empty()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }
}
