import domain.Lotto;
import domain.LottoService;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    private static LottoService lottoService;
    public static void main(String[] args) {
        setLottoService();
        int lottoTicketCount = lottoService.makeLottoTicketCount(new InputView().inputMoney());
        List<Integer> lottoNumberList = lottoService.makeLottoNumberList();
        List<List<Integer>> lottoTickets = lottoService.makeLottoTickets(lottoTicketCount, lottoNumberList);
        Lotto lotto = new Lotto(lottoTicketCount, lottoNumberList, lottoTickets);
        new OutputView().outputLottoTickets(lotto);
    }

    public static void setLottoService() {
        lottoService = new LottoService();
    }
}
