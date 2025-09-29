package controller;

import domain.Lotto;
import domain.LottoService;
import domain.LottoTicketCount;
import domain.LottoTickets;
import domain.Money;
import domain.MatchCount;
import domain.LottoTotalPrice;
import domain.ProfitRate;
import domain.LottoProfit;
import view.InputView;
import view.OutputView;
import view.ResultView;


public class LottoController {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    public void run(){
        outputView.printWonMessage();
        Money money = new Money(inputView.inputMoney());

        LottoTickets lottoTickets = buyLotto(money);
        checkLotto(lottoTickets,money);
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
        Lotto lottoAnswerObj = lottoService.parseLottoAnswer(lottoAnswer);
        MatchCount matchCount = lottoService.calculateMatchCount(lottoTickets.getTickets(), lottoAnswerObj);

        int totalSum = LottoProfit.LottoSum(matchCount);
        ProfitRate profitRate = new ProfitRate(money, new LottoTotalPrice(totalSum));
        resultView.printLottoProfit(profitRate.getProfitRate());

        resultView.printLottoMatch(matchCount);
        resultView.printLottoProfit(profitRate.getProfitRate());
    }
}
