import model.AutoCount;
import model.AutoLottoTicket;
import org.junit.jupiter.api.Test;
import util.TestLottoGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoTicketTest {

    @Test
    void 지정된_개수만큼_로또가_자동으로_생성된다() {
        TestLottoGenerator generator = new TestLottoGenerator();
        AutoCount count = new AutoCount(3);

        AutoLottoTicket ticket = new AutoLottoTicket(generator, count);

        assertThat(ticket.getLottos()).hasSize(3);
    }
}
