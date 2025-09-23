package controller;
import domain.*;
import domain.Lotto;
import view.InputView;
import view.OutputView;
import view.ResultView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class LottoController {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    public void run() {
        outputView.printWonMessage();
        int money = inputView.inputMoney();
        int ticketNumber = MoenyToTicket.MoenyToTicket(money);

        resultView.printTicketNumbers(ticketNumber);

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
        double profitRate = LottoProfit.LottoProfit(money, totalSum);

        resultView.printLottoMatch(matchCount);
        resultView.printLottoProfit(profitRate);

    }
}
