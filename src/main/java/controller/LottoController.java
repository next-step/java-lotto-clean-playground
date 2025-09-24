package controller;
import domain.*;
import view.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    public void run() {
        outputView.printWonMessage();
        Money money = new Money(inputView.inputMoney());
        LottoTicketCount ticketNumber = MoneyToTicket.MoneyToTicket(money);
        resultView.printTicketNumbers(ticketNumber.getCount());

        LottoTickets lottoTickets = new LottoTickets(ticketNumber);
        for (Lotto lotto : lottoTickets.getTickets()) {
            System.out.println(lotto);
        }

        outputView.printLottoAnswer();
        String lottoAnswer = inputView.inputLottoAnswer();

        List<Integer> lottoAnswerList = Arrays.stream(lottoAnswer.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Lotto lottoAnswerobj = new Lotto(lottoAnswerList);
        MatchCount matchCount = MatchCount.countAllMatches(lottoTickets.getTickets(), lottoAnswerobj);

        int totalSum = LottoProfit.LottoSum(matchCount);
        ProfitRate profitRate = new ProfitRate(money, new LottoTotalPrice(totalSum));
        resultView.printLottoProfit(profitRate.getProfitRate());

        resultView.printLottoMatch(matchCount);
        resultView.printLottoProfit(profitRate);
    }
}
