package controller;

import domain.draw.RandomLottoNumber;
import domain.lotto.LottoSeller;
import domain.lotto.Win;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private Win win;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {

        int payment = initPayment();

        LottoSeller seller = new LottoSeller(payment, new RandomLottoNumber());

        payAndNoticeChange(seller);

        OutputView.printLottoTickets(seller.getTickets());
        OutputView.newLine();

        OutputView.printLastWeekWinedNumbersNotice();
        List<Integer> winningNumbers = inputView.lastWeekWinningNumbers();

        win = new Win(winningNumbers, seller.getTickets());

        OutputView.newLine();
        OutputView.printWinningStatics(win.getWinningStatics());
    }

    private void payAndNoticeChange(LottoSeller seller) {
        OutputView.newLine();
        OutputView.printLottoAmount(seller.getAmount());

        if (seller.getChange() > 0) {
            System.out.printf("잔돈은 %d원 입니다.\n", seller.getChange());
        }
    }

    private int initPayment() {
        int payment;

        do {
            OutputView.printPaymentNotice();
            payment = inputView.payment();
        } while (payment <= 0);
        return payment;
    }
}
