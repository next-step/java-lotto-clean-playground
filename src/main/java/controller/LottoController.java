package controller;

import domain.draw.RandomLottoNumber;
import domain.lotto.Lotto;
import domain.lotto.LottoSeller;
import domain.lotto.BuyingLotto;
import domain.lotto.WinningLotto;
import domain.lotto.collection.LottoTickets;
import domain.lotto.collection.WinningStatistics;
import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.money.Money;
import domain.lotto.wrap.money.Price;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final Price PRICE = new Price(1_000);
    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {

        BuyingLotto buyingLotto = initPayment(PRICE);
        List<Lotto> manualLottos = manualLottosInitialize(buyingLotto);

        LottoSeller seller = new LottoSeller(buyingLotto, manualLottos, new RandomLottoNumber());

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

        if (seller.hasChange()) {
            System.out.printf("잔돈은 %d원 입니다.\n", seller.getChange());
        }
    }

    private BuyingLotto initPayment(Price price) {
        BuyingLotto buyingLotto;

        do {
            buyingLotto = requestPayment(price);
        } while (buyingLotto == null);
        return buyingLotto;
    }

    private BuyingLotto requestPayment(Price price) {
        try {
            OutputView.printPaymentNotice();
            return new BuyingLotto(inputView.payment(), price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private int initManualCount(BuyingLotto buyingLotto) {
        int count;

        do {
            OutputView.printManualCountNotice();
            count = inputView.manualCount();
        } while (count > buyingLotto.purchasableCount());
        return count;
    }

    private List<Lotto> manualLottosInitialize(BuyingLotto buyingLotto) {
        int manualCount = initManualCount(buyingLotto);
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
