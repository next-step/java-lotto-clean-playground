package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;

    public void run() {
        int money = Integer.parseInt(InputView.inputMoney());
        LottoTickets tickets = purchase(money);
        OutputView.printTickets(tickets);

        Lotto winningLotto = askWinningLotto();
        calculateResult(tickets, winningLotto, money);
    }

    private LottoTickets purchase(int money) {
        int count = money / LOTTO_PRICE;
        OutputView.printTicketCount(count);
        List<Lotto> tickets = IntStream.range(0, count)
                .mapToObj(i -> generateRandomLotto())
                .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    private Lotto generateRandomLotto() {
        List<Integer> allNumbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
        Collections.shuffle(allNumbers);
        List<LottoNumber> lottoNumbers = allNumbers.subList(0, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    private Lotto askWinningLotto() {
        String input = InputView.inputWinningNumbers();
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    private void calculateResult(LottoTickets tickets, Lotto winningLotto, int money) {
        Map<Rank, Long> result = tickets.getTickets().stream()
                .map(ticket -> Rank.valueOf(ticket.countMatch(winningLotto)))
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        long totalPrize = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getWinningMoney() * entry.getValue())
                .sum();

        OutputView.printStatistics(result, (double) totalPrize / money);
    }
}
