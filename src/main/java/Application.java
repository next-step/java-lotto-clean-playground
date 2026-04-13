import domain.lotto.*;
import number_generator.NumberListGenerator;
import number_generator.RandomNumberListGenerator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Payment payment = inputView.readPayment();
        TicketCount totalTicketCount = payment.createTicketCount();
        TicketCount manualTicketCount = inputView.readManualTicketCount();
        TicketCount randomTicketCount = new TicketCount(totalTicketCount.getValue() - manualTicketCount.getValue());

        TicketGenerator ticketGenerator = new TicketGenerator(manualTicketCount, randomTicketCount);
        NumberListGenerator randomNumberListGenerator = new RandomNumberListGenerator();

        List<Ticket> manualTickets = ticketGenerator.createManualTickets(inputView.readManualTickets(manualTicketCount));
        List<Ticket> randomTickets = ticketGenerator.createRandomTickets(randomNumberListGenerator);

        TicketBundle ticketBundle = new TicketBundle();

        ticketBundle.addTickets(manualTickets);
        ticketBundle.addTickets(randomTickets);
        outputView.showGeneratedTickets(ticketGenerator, ticketBundle);

        WinnerBalls winnerBalls = inputView.readWinnerBalls();

        Result result = ticketBundle.createResult(winnerBalls);
        outputView.showResults(payment, result);
    }
}
