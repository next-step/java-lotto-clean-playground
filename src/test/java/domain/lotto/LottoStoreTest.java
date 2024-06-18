package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoStoreTest {

    @Test
    @DisplayName("복권방에서 로또를 구입한다.")
    void 복권방에서_로또를_구입한다() {
        LottoStore lottoStore = new LottoStore(new AutoLottoGenerator());
        List<LottoTicket> lottoTickets = lottoStore.sellLottos(new Money(14000));

        Assertions.assertThat(lottoTickets).hasSize(14);
    }
}
