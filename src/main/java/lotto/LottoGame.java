package lotto;

import lotto.domain.*;
import lotto.view.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {
    public void run() {
        int money = InputView.inputMoney();
        LottoTickets tickets = purchaseTickets(money);
        OutputView.printTickets(tickets);

        WinningLotto winningLotto = askWinningLotto();
        showResult(tickets, winningLotto, money);

    }

    private LottoTickets purchaseTickets(int money) {
        int manualCount = InputView.inputManualCount();
        List<Lotto> manualLottos = inputManualLottos(manualCount);

        int autoCount = (money / 1000) - manualCount;
        OutputView.printPurchaseSummary(manualCount, autoCount);

        return LottoTickets.createCombined(manualLottos, autoCount, new RandomLottoNumberStrategy());
    }

    private List<Lotto> inputManualLottos(int count) {
        InputView.printManualInputMessage();
        return IntStream.range(0, count)
                .mapToObj(i -> Lotto.from(InputView.inputNumbers()))
                .collect(Collectors.toList());
    }

    private WinningLotto askWinningLotto() {
        Lotto winningNumbers = Lotto.from(InputView.inputWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.valueOf(InputView.inputBonusNumber());
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private void showResult(LottoTickets tickets, WinningLotto winningLotto, int money) {
        Map<Rank, Long> rankResult = tickets.matchAll(winningLotto);
        double yield = tickets.calculateYield(rankResult, money);
        OutputView.printStatistics(rankResult, yield);
    }
}
