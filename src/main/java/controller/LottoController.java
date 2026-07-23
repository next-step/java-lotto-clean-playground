package controller;

import domain.Lotto;
import domain.LottoMachine;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        int purchaseAmount = inputView.readPurchaseAmount();
        List<Lotto> purchasedLottoTickets = lottoMachine.buy(purchaseAmount);
        outputView.printPurchasedLottoTickets(purchasedLottoTickets);
    }
}
