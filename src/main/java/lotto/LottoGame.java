package lotto;

import lotto.domain.*;
import lotto.view.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {
    public void run() {
        int money = InputView.inputMoney();
        int manualCount = InputView.inputManualCount();

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> manualTickets = IntStream.range(0, manualCount)
                .mapToObj(i -> Lotto.from(InputView.inputNumbers())).collect(Collectors.toList());

        LottoTickets totalTickets = LottoTickets.createCombined(manualTickets, (money / 1000) - manualCount);
        OutputView.printPurchaseSummary(manualCount, (money / 1000) - manualCount);
        OutputView.printTickets(totalTickets);

        WinningLotto winningLotto = new WinningLotto(Lotto.from(InputView.inputWinningNumbers()), LottoNumber.valueOf(InputView.inputBonusNumber()));
        Map<Rank, Long> rankResult = totalTickets.matchAll(winningLotto);
        double yield = totalTickets.calculateYield(rankResult, money);

        // 4. 출력
        OutputView.printStatistics(rankResult, yield);
    }
}
