package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("구입 금액에 맞는 개수의 로또를 생성한다")
    void createLottoByPurchaseAmount() {
        LottoMachine lottoMachine = new LottoMachine();

        List<Lotto> purchasedLottoTickets = lottoMachine.buy(14000);

        assertThat(purchasedLottoTickets).hasSize(14);
    }

    @Test
    @DisplayName("구입 금액이 0원 이하면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotPositive() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.buy(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 0원보다 커야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotDivisibleByThousand() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.buy(9500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }
}
