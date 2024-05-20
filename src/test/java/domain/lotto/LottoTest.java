package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

public class LottoTest {
    @Test
    @DisplayName("로또 객체 생성")
    public void lottoTest() {
        //given
        int expectedLottoTicketCount = 3;
        String expectedMoney = "3000";

        //when
        LottoService lottoService = new LottoService(new InputView(), new OutputView());
        Lotto lotto = new Lotto(expectedMoney);

        //then
        assertThat(Integer.parseInt(expectedMoney)).isEqualTo(lotto.getLottoMoney());
    }
}
