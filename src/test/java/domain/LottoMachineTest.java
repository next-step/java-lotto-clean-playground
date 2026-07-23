package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}
