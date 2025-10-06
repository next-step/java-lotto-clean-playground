package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoService;
import domain.LottoTicketCount;
import domain.Lottos;
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
        Money money = null;
        while (money == null) {
            try {
                money = new Money(inputView.inputMoney());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }

        Lottos lottoTickets = buyLotto(money);
        checkLotto(lottoTickets, money);
    }


    public Lottos buyLotto(Money money) {
        LottoTicketCount ticketNumber = money.getTicketCount();
        resultView.printTicketNumbers(ticketNumber.getCount());

        outputView.printManualCount();
        int manualCount;

        while (true) {
            try {
                manualCount = inputView.inputManualCount();
                ticketNumber.validateManualCount(manualCount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }

        List<Lotto> manualLottos = new ArrayList<>();
        outputView.printManualNumbers();

        LottoService lottoService = new LottoService();
        for (int i = 0; i < manualCount; i++) {
            while (true) {
                try {
                    String manualNumbers = inputView.inputManualNumbers();
                    Lotto lotto = lottoService.parseLottoAnswer(manualNumbers);
                    manualLottos.add(lotto);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("다시 입력해주세요.");
                }
            }
        }

        int autoCount = ticketNumber.getCount() - manualCount;

        resultView.printManualAuto(manualCount, autoCount);
        outputView.lottoResult();
        Lottos lottoTickets = Lottos.createMixedTickets(manualLottos, autoCount);

        for (Lotto lotto : lottoTickets.getTickets()) {
            System.out.println(lotto);
        }

        return lottoTickets;
    }


    public void checkLotto(Lottos ticketAutoCount, Money money) {
        outputView.printLottoAnswer();

        LottoService lottoService = new LottoService();

        Lotto lottoAnswerObj = null;
        while (lottoAnswerObj == null) {
            try {
                String lottoAnswer = inputView.inputLottoAnswer();
                lottoAnswerObj = lottoService.parseLottoAnswer(lottoAnswer);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }

        outputView.printBonusMessage();

        LottoNumber bonusBall;
        while (true) {
            try {
                int bonusBallNumber = inputView.inputBonusNumber();
                bonusBall = new LottoNumber(bonusBallNumber);
                lottoService.validateBonusBall(lottoAnswerObj, bonusBall);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }


        MatchCount matchCount = lottoService.calculateMatchCount(
                ticketAutoCount.getTickets(),
                lottoAnswerObj,
                bonusBall);

        ProfitRate profitRate = new ProfitRate(money, new LottoTotalPrice(matchCount));

        outputView.lottoResult();
        resultView.printLottoMatch(matchCount);
        resultView.printLottoProfit(profitRate.getProfitRate());
    }
}
