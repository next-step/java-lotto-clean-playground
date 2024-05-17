package controller;

import domain.LottoTicket;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int money = inputView.getMoney();
        LottoTicket lottoTicket = new LottoTicket(money);
        outputView.displayLottoTickets(lottoTicket.getLottoTicket());
    }

    public static void main(String[] args) {
        new LottoController().run();
    }
}
