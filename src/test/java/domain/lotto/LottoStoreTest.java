package domain.lotto;

import domain.common.Money;
import domain.lotto.generator.AutoLottoGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoStoreTest {

//    @Test
//    @DisplayName("복권방에서 로또를 구입한다.")
//    void 복권방에서_로또를_구입한다() {
//        LottoStore lottoStore = new LottoStore(new AutoLottoGenerator());
//        List<LottoTicket> lottoTickets = lottoStore.sellLottos(new Money(14000));
//
//        Assertions.assertThat(lottoTickets).hasSize(14);
//    }
//
//    @Test
//    @DisplayName("1000원 단위가 아닌 돈이 들어오면 예외 발생")
//    void 입금액이_1000원_단위가_아닌_돈이_들어오면_예외_발생() {
//        LottoStore lottoStore = new LottoStore(new AutoLottoGenerator());
//
//        Assertions.assertThatThrownBy(() -> lottoStore.sellLottos(new Money(14500)))
//            .isInstanceOf(IllegalArgumentException.class);
//    }
}
