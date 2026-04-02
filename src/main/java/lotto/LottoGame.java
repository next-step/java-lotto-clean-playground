package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;

    public void run() {
        int money = Integer.parseInt(InputView.inputMoney());
        LottoTickets tickets = purchase(money); //LottoTickets 타입의 tickets라는 변수를 만들고, purchase(money)의 결과를 넣는다
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
        String input = InputView.inputWinningNumbers(); //문자열을 입력받음( winningLotto)
        List<Integer> numbers = java.util.Arrays.stream(input.split(","))
                .map(String::trim) //공백 제거
                .map(Integer::parseInt) //숫자로 변환
                .collect(java.util.stream.Collectors.toList()); //리스트로 변환
        return Lotto.from(numbers); //Lotto 객체로 변환
    }

    private void showResult(LottoTickets tickets, Lotto winningLotto, int money) {
        LottoResult lottoResult = new LottoResult(tickets.matchAll(winningLotto));
        double yield = lottoResult.calculateYield(money);

        OutputView.printStatistics(lottoResult.getResult(), yield);
    }
}
