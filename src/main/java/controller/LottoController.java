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

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final Money PRICE = new Money(1_000);
    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {

        Money payment = initPayment(PRICE);
        List<Lotto> manualLottos = manualLottosInitialize(payment);

        LottoSeller seller = new LottoSeller(PRICE, payment, manualLottos, new RandomLottoNumber());

        noticeLottoAmountAndChange(seller);

        LottoTickets tickets = ticketsInitialize(seller);
        Lotto lastWeekWinningNumber = lastWeekWinningNumberInitialize();

        LottoNumber bonus = lastWeekBonusBallInitialize();

        printResult(lastWeekWinningNumber, bonus, tickets, seller);
    }

    private void printResult(Lotto lastWeekWinningNumber, LottoNumber bonus, LottoTickets tickets, LottoSeller seller) {
        WinningLotto winningLotto = new WinningLotto(lastWeekWinningNumber, bonus);
        WinningStatistics statistics = tickets.match(winningLotto);

        OutputView.newLine();
        OutputView.printWinningStatics(statistics, seller.getPaid());
    }

    private LottoNumber lastWeekBonusBallInitialize() {
        OutputView.printBonusBallNotice();
        LottoNumber bonus = inputView.bonusNumber();
        return bonus;
    }

    private Lotto lastWeekWinningNumberInitialize() {
        OutputView.printLastWeekWinedNumbersNotice();

        Lotto lastWeekWinningNumber = inputView.lastWeekWinningNumbers();
        OutputView.newLine();
        return lastWeekWinningNumber;
    }

    private LottoTickets ticketsInitialize(LottoSeller seller) {
        LottoTickets tickets = seller.getTickets();
        OutputView.printLottoTickets(tickets);
        OutputView.newLine();
        return tickets;
    }

    private void noticeLottoAmountAndChange(LottoSeller seller) {
        OutputView.newLine();
        OutputView.printLottoAmount(seller.getManualCount(), seller.getAutoCount());

        if (seller.getChange() > 0) {
            System.out.printf("잔돈은 %d원 입니다.\n", seller.getChange());
        }
    }

    private Money initPayment(Money price) {
        Money payment;

        do {
            OutputView.printPaymentNotice();
            payment = inputView.payment(price);
        } while (payment.compareTo(price) < 0);
        return payment;
    }

    private int initManualCount(Money payment) {
        int count;

        do {
            OutputView.printManualCountNotice();
            count = inputView.manualCount();
        } while (count > payment.countPurchasable(PRICE));
        return count;
    }

    private List<Lotto> manualLottosInitialize(Money payment) {
        int manualCount = initManualCount(payment);
        List<Lotto> manualLottos = new ArrayList<>();

        if (manualCount > 0) {
            OutputView.printManualNumbersNotice();
        }
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(inputView.manualLottoNumbers());
        }
        return manualLottos;
    }
}
