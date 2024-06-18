package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또 티켓을 자동 발급한다.")
    void 로또_티켓을_자동_발급한다() {
        LottoGenerator autoLottoGenerator = new AutoLottoGenerator();

        LottoTicket issuedLottoTicket = autoLottoGenerator.issue();

        Assertions.assertThat(issuedLottoTicket).isNotNull();
        Assertions.assertThat(issuedLottoTicket.size()).isEqualTo(6);
    }
}
