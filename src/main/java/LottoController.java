import domain.LottoGenerator;
import domain.LottoTicket;
import domain.Money;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator generator;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void run() {
        Money money = inputView.readMoney();
        int count = money.ticketCount();
        List<LottoTicket> tickets = generator.generate(count);
        outputView.printPurchased(count);
        outputView.printTickets(tickets);
    }
}
