package controller;

import model.Lotto;
import model.LottoGenerator;
import model.LottoTicketMachine;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final ResultView resultView;

    public LottoController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        LottoTicketMachine lottoTicketMachine = new LottoTicketMachine(purchaseAmount, new LottoGenerator());
        List<Lotto> lottery = lottoTicketMachine.generateLottery();
        resultView.printTicketCount(lottoTicketMachine.getTicketCount());
        resultView.printLottery(lottery);
    }
}
