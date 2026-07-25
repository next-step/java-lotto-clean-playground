package controller;

import domain.draw.RandomLottoNumber;
import domain.lotto.Lotto;
import domain.lotto.LottoSeller;
import domain.lotto.WinningLotto;
import domain.lotto.collection.LottoTickets;
import domain.lotto.collection.WinningStatistics;
import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.Money;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {

        Money payment = initPayment();

        LottoSeller seller = new LottoSeller(payment, new RandomLottoNumber());

        payAndNoticeChange(seller);

        LottoTickets tickets = seller.getTickets();
        OutputView.printLottoTickets(tickets);
        OutputView.newLine();

        OutputView.printLastWeekWinedNumbersNotice();

        Lotto lastWeekWinningNumber = inputView.lastWeekWinningNumbers();
        OutputView.newLine();

        OutputView.printBonusBallNotice();
        LottoNumber bonus = inputView.bonusNumber();

        WinningLotto winningLotto = new WinningLotto(lastWeekWinningNumber, bonus);
        WinningStatistics statistics = tickets.match(winningLotto);

        OutputView.newLine();
        OutputView.printWinningStatics(statistics, seller.getPaid());
    }

    private void payAndNoticeChange(LottoSeller seller) {
        OutputView.newLine();
        OutputView.printLottoAmount(seller.getAmount());

        if (seller.getChange() > 0) {
            System.out.printf("잔돈은 %d원 입니다.\n", seller.getChange());
        }
    }

    private Money initPayment() {
        Money payment;

        do {
            OutputView.printPaymentNotice();
            payment = inputView.payment();
        } while (payment.getAmount() <= 0);
        return payment;
    }
}
