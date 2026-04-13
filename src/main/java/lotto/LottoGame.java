package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

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
        return LottoTickets.generate(count);
    }

    private Lotto askWinningLotto() {
        String input = InputView.inputWinningNumbers();
        List<Integer> numbers = java.util.Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(java.util.stream.Collectors.toList());
        return Lotto.from(numbers);
    }

    private void showResult(LottoTickets tickets, Lotto winningLotto, int money) {
        LottoResult lottoResult = new LottoResult(tickets.matchAll(winningLotto));
        double yield = lottoResult.calculateYield(money);

        OutputView.printStatistics(lottoResult.getResult(), yield);
    }
}
