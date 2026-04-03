import domain.wrappers.TicketCount;
import number_generator.NumberListGenerator;
import number_generator.RandomLottoNumberListGenerator;
import domain.Lotto;
import domain.wrappers.LottoResult;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Lotto lotto = new Lotto();
        NumberListGenerator randomLottoNumberListGenerator = new RandomLottoNumberListGenerator();
        TicketCount ticketCount = new TicketCount(inputView.readLottoPayment());

        lotto.createRandomTickets(ticketCount, randomLottoNumberListGenerator);
        outputView.showLottoTickets(lotto);
        LottoResult result = lotto.createLottoResult(inputView.readWinnerTicket());
        outputView.showLottoResults(ticketCount, result);
    }
}
