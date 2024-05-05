import domain.lotto.Lotto;
import domain.lotto.LottoService;
import domain.lottoTicket.LottoTicket;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        int money = new InputView().inputMoney();

        LottoService lottoService = new LottoService();

        Lotto lotto = new Lotto(lottoService.countLottoTickets(money));

        List<LottoTicket> lottoTickets = lottoService.generateLottoTicketList(lotto);

        new OutputView().outputLottoTickets(lotto, lottoTickets);
    }

}
