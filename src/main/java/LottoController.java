import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public final class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator generator;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void run() {
        Money money = Money.from(inputView.readMoney());
        int totalCount = money.ticketCount();

        int manualCount = inputView.readManualCount(totalCount);
        List<LottoTicket> manualTickets = inputView.readManualTickets(manualCount);

        int autoCount = totalCount - manualCount;
        List<LottoTicket> autoTickets = generator.generate(autoCount);

        List<LottoTicket> allTickets = merge(manualTickets, autoTickets);
        outputView.printPurchased(manualCount, autoCount);
        outputView.printTickets(allTickets);

        WinningNumbers winning = inputView.readWinningNumbers();

        LottoResult result = LottoResult.of(allTickets, winning, money);
        outputView.printResult(result);
    }
    private List<LottoTicket> merge(List<LottoTicket> manual, List<LottoTicket> autoTickets) {
        List<LottoTicket> all = new ArrayList<>();
        all.addAll(manual);
        all.addAll(autoTickets);
        return all;
    }
}
