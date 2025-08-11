import exception.CustomException;
import exception.ErrorMessage;
import model.Lotto;
import model.LottoNumber;
import model.ManualLottoTicket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoTicketTest {

    @Test
    void 정상적인_수동_로또_번호_리스트를_통해_객체를_생성한다() {
        List<String> manualInputs = List.of(
            "1,2,3,4,5,6",
            "7,8,9,10,11,12"
        );

        ManualLottoTicket ticket = new ManualLottoTicket(manualInputs);
        List<Lotto> lottos = ticket.getLottos();

        assertThat(lottos).hasSize(2);

        assertThat(lottos.get(0).getLottoNumbers())
            .extracting(LottoNumber::getNumber)
            .containsExactly(1, 2, 3, 4, 5, 6);

        assertThat(lottos.get(1).getLottoNumbers())
            .extracting(LottoNumber::getNumber)
            .containsExactly(7, 8, 9, 10, 11, 12);
    }

    @Test
    void 전달한_문자열이_정수가_아닌_경우_예외가_발생한다() {
        List<String> manualInputs = List.of(
            "1,2,3,4,5,6",
            "7,8,9,10,11,abc"
        );

        assertThatThrownBy(() -> new ManualLottoTicket(manualInputs))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
    }

    @Test
    void 전달한_문자열이_로또_번호의_범위를_벗어난_경우_예외가_발생한다() {
        List<String> manualInputs = List.of(
            "1,2,3,4,5,-6",
            "7,8,9,100,11,12"
        );

        assertThatThrownBy(() -> new ManualLottoTicket(manualInputs))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
    }
}
