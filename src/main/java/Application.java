import domain.lotto.*;
import number_generator.NumberListGenerator;
import number_generator.RandomNumberListGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        TicketBundle ticketBundle = new TicketBundle();

        Payment payment = inputView.readPayment();
        TicketCount totalTicketCount = payment.createTicketCount();
        TicketCount manualTicketCount = inputView.readManualTicketCount();
        TicketCount randomTicketCount = new TicketCount(totalTicketCount.getValue() - manualTicketCount.getValue());

        TicketGenerator ticketGenerator = new TicketGenerator(manualTicketCount, randomTicketCount);
        NumberListGenerator randomNumberListGenerator = new RandomNumberListGenerator();

        ticketBundle.addTickets(ticketGenerator.createManualTickets(inputView.readManualTickets(manualTicketCount)));
        ticketBundle.addTickets(ticketGenerator.createRandomTickets(randomNumberListGenerator));


        outputView.showGeneratedTickets(ticketGenerator, ticketBundle);
        Result result = ticketBundle.createResult(inputView.readWinnerBalls());
        outputView.showResults(payment, result);
    }
}
