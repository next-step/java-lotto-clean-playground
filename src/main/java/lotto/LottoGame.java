package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;

    public void run() {
        int money = Integer.parseInt(InputView.inputMoney());
        LottoTickets tickets = purchase(money);
        OutputView.printTickets(tickets);

        Lotto winningLotto = askWinningLotto();
        showResult(tickets, winningLotto, money);
    }

    private LottoTickets purchase(int money) {
        int count = money / LOTTO_PRICE;
        OutputView.printTicketCount(count);
        List<Lotto> tickets = IntStream.range(0, count)
                .mapToObj(i -> LottoMachine.generate()) // 기계에게 생성을 시킴
                .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    private Lotto askWinningLotto() {
        String input = InputView.inputWinningNumbers();
        return LottoFactory.createManualLotto(input);
    }

    private void showResult(LottoTickets tickets, Lotto winningLotto, int money) {
        LottoResult lottoResult = new LottoResult(tickets.matchAll(winningLotto));

        double yield = lottoResult.calculateYield(money);

        OutputView.printStatistics(lottoResult.getResult(), yield);
    }
}
