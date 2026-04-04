import domain.wrappers.TicketCount;
import number_generator.LottoNumberListGenerator;
import number_generator.LottoRandomNumberListGenerator;
import domain.LottoTicketBundle;
import domain.wrappers.LottoResult;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoTicketBundle lotto = new LottoTicketBundle();
        LottoNumberListGenerator randomLottoLottoNumberListGenerator = new LottoRandomNumberListGenerator();
        TicketCount ticketCount = new TicketCount(inputView.readLottoPayment());

        lotto.createRandomTickets(ticketCount, randomLottoLottoNumberListGenerator);
        outputView.showLottoTickets(lotto);
        LottoResult result = lotto.createLottoResult(inputView.readWinnerTicket());
        outputView.showLottoResults(ticketCount, result);
    }
}
