package controller;

import domain.Cashier;
import domain.Lotto;
import domain.LottoResult;
import domain.LottoTicket;
import domain.ManualTicketCount;
import domain.Price;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final Cashier cashier;

    public Controller(InputView inputView, OutputView outputView, Cashier cashier) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cashier = cashier;
    }

    public void run() {
        Price price = inputView.inputPrice();
        ManualTicketCount manualTicketCount = inputView.inputManualTicketCount(price);
        List<LottoTicket> manualTickets = new ArrayList<>();
        if (manualTicketCount.getCount() != 0) {
            manualTickets = inputView.inputManualTickets(manualTicketCount);
        }
        Lotto lotto = cashier.generateTickets(price, manualTickets);
        outputView.showLottoTickets(lotto, manualTicketCount);

        LottoResult result = lotto.getResults(inputView.inputWinnerTicket(), inputView.inputBonusNumber());
        outputView.showLottoResults(result, cashier.getProfitRate(result, price));
    }
}
