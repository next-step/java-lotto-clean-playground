package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoService;
import domain.LottoTicketCount;
import domain.LottoTickets;
import domain.LottoTotalPrice;
import domain.Money;
import domain.MatchCount;
import domain.ProfitRate;
import view.InputView;
import view.OutputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;


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

        outputView.printManualCount();
        int manualCount = inputView.inputManualCount();

        List<Lotto> manualLottos = new ArrayList<>();
        outputView.printManualNumbers();

        LottoService lottoService = new LottoService();
        for (int i = 0; i < manualCount; i++) {
            String manualNumbers = inputView.inputManualNumbers();
            manualLottos.add(lottoService.parseLottoAnswer(manualNumbers));

        }

        int autoCount = ticketNumber.getCount() - manualCount;

        resultView.printManualAuto(manualCount, autoCount);
        outputView.lottoResult();
        LottoTickets lottoTickets = LottoTickets.createMixedTickets(manualLottos, autoCount);

        for (Lotto lotto : lottoTickets.getTickets()) {
            System.out.println(lotto);
        }

        return lottoTickets;
    }


    public void checkLotto(LottoTickets tickektAutoCount, Money money) {
        outputView.printLottoAnswer();

        LottoService lottoService = new LottoService();
        String lottoAnswer = inputView.inputLottoAnswer();
        outputView.printBonusMessage();
        int bonusBallNumber = inputView.inputBonusNumber();
        LottoNumber bonuseBall = new LottoNumber(bonusBallNumber);

        Lotto lottoAnswerObj = lottoService.parseLottoAnswer(lottoAnswer);
        lottoService.validateBonusBall(lottoAnswerObj, bonuseBall);

        MatchCount matchCount = lottoService.calculateMatchCount(tickektAutoCount.getTickets(), lottoAnswerObj, bonuseBall);

        ProfitRate profitRate = new ProfitRate(money, new LottoTotalPrice(matchCount));

        outputView.lottoResult();
        resultView.printLottoMatch(matchCount);
        resultView.printLottoProfit(profitRate.getProfitRate());
    }
}
