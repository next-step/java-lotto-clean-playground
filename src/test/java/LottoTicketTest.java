import model.*;
import org.junit.jupiter.api.Test;
import util.TestLottoGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {

    @Test
    void 수동_자동_로또_병합_테스트() {
        ManualLottoTicket manual = new ManualLottoTicket(List.of(
            "1,2,3,4,5,6",
            "7,8,9,10,11,12"
        ));

        AutoLottoTicket auto = new AutoLottoTicket(new TestLottoGenerator(), new AutoCount(1));

        LottoTicket merged = LottoTicket.merge(manual, auto);
        List<Lotto> mergedLottos = merged.getLottos();

        assertThat(mergedLottos).hasSize(3);

        assertThat(mergedLottos.get(0).getLottoNumbers())
            .extracting(LottoNumber::getNumber)
            .containsExactly(1, 2, 3, 4, 5, 6);

        assertThat(mergedLottos.get(1).getLottoNumbers())
            .extracting(LottoNumber::getNumber)
            .containsExactly(7, 8, 9, 10, 11, 12);

        assertThat(mergedLottos.get(2).getLottoNumbers())
            .extracting(LottoNumber::getNumber)
            .containsExactly(1, 2, 3, 4, 5, 6);
    }
}
