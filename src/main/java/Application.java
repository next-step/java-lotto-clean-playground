import domain.lotto.wrappers.TicketCount;
import number_generator.NumberListGenerator;
import number_generator.RandomNumberListGenerator;
import domain.lotto.TicketBundle;
import domain.lotto.wrappers.Result;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        TicketBundle ticketBundle = new TicketBundle();
        NumberListGenerator randomNumberListGenerator = new RandomNumberListGenerator();
        TicketCount ticketCount = new TicketCount(inputView.readLottoPayment());

        ticketBundle.createRandomTickets(ticketCount, randomNumberListGenerator);
        outputView.showLottoTickets(ticketBundle);
        Result result = ticketBundle.createResult(inputView.readWinnerTicketPair());
        outputView.showLottoResults(ticketCount, result);
    }
}
