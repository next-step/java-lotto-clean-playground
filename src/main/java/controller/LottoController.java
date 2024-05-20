package controller;

import domain.LottoTicket;
import domain.LottoTicketStore;
import domain.NumberGenerator;
import domain.RandomNumberGenerator;
import view.InputView;
import view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public LottoController() {
        this.numberGenerator = new RandomNumberGenerator();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int money = inputView.getMoney();
        purchasedLotto(money);
    }

    public static void main(String[] args) {
        new LottoController().run();
    }

    private void purchasedLotto(int money) {
        LottoTicket lottoTicket = new LottoTicket(money, numberGenerator);
        List<List<Integer>> tickets = lottoTicket.getLottoTicket().stream()
                .map(LottoTicketStore::getLottoNumber)
                .collect(Collectors.toList());
        outputView.displayLottoTickets(tickets);
    }
}
