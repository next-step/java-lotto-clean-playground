package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.ResultView;


public class LottoController {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    public void run() {
        outputView.printWonMessage();
        Money money = new Money(inputView.inputMoney());

        LottoTickets lottoTickets = buyLotto(money);
        checkLotto(lottoTickets, money);
    }


    public LottoTickets buyLotto(Money money) {

        LottoTicketCount ticketNumber = Money.getTicketCount(money);
        resultView.printTicketNumbers(ticketNumber.getCount());

        outputView.lottoResult();

        LottoTickets lottoTickets = new LottoTickets(ticketNumber);
        for (Lotto lotto : lottoTickets.getTickets()) {
            System.out.println(lotto);
        }
        return lottoTickets;
    }

    public void checkLotto(LottoTickets lottoTickets, Money money) {
        outputView.printLottoAnswer();

        LottoService lottoService = new LottoService();
        String lottoAnswer = inputView.inputLottoAnswer();
        outputView.printBonusMessage();
        int bonusBallNumber = inputView.inputBonusNumber();
        LottoNumber bonuseBall = new LottoNumber(bonusBallNumber);

        Lotto lottoAnswerObj = lottoService.parseLottoAnswer(lottoAnswer);
        MatchCount matchCount = lottoService.calculateMatchCount(lottoTickets.getTickets(), lottoAnswerObj, bonuseBall);

        ProfitRate profitRate = new ProfitRate(money, new LottoTotalPrice(matchCount));

        outputView.lottoResult();
        resultView.printLottoMatch(matchCount);
        resultView.printLottoProfit(profitRate.getProfitRate());
    }
}
