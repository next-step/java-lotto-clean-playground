import domain.lotto.TicketGenerator;
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

        TicketCount totalTicketCount = new TicketCount(inputView.readPayment());
        TicketCount manualTicketCount = inputView.readManualTicketCount();
        TicketCount randomTicketCount = new TicketCount(totalTicketCount.getValue() - manualTicketCount.getValue());

        TicketGenerator ticketGenerator = new TicketGenerator(manualTicketCount, randomTicketCount);
        NumberListGenerator randomNumberListGenerator = new RandomNumberListGenerator();

        ticketBundle.addTickets(ticketGenerator.createManualTickets(inputView.readManualTickets(manualTicketCount)));
        ticketBundle.addTickets(ticketGenerator.createRandomTickets(randomNumberListGenerator));


        outputView.showGeneratedTickets(ticketGenerator, ticketBundle);
        Result result = ticketBundle.createResult(inputView.readWinnerBalls());
        outputView.showResults(totalTicketCount, result);
    }
}
