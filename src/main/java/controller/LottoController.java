package controller;

import domain.draw.RandomLottoNumber;
import domain.lotto.LottoSeller;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {

        int payment;

        do {
            OutputView.printPaymentNotice();
            payment = inputView.payment();
        } while (payment <= 0);

        LottoSeller seller = new LottoSeller(payment, new RandomLottoNumber());

        OutputView.printLottoAmount(seller.getTickets().size());

        if (seller.getChange() > 0) {
            System.out.printf("잔돈은 %d원 입니다.\n", seller.getChange());
        }

        OutputView.printLottoTickets(seller.getTickets());
    }
}
