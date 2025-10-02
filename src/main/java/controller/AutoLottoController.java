package controller;

import inputView.InputView;
import inputView.OutputView;
import inputView.Price;
import model.*;

import java.util.List;
import java.util.Map;

public class AutoLottoController {
    private final LottoBusiness lottoBusiness = new LottoBusiness();
    private final OutputView outView = new OutputView();
    private final InputView inputView = new InputView();

    private Price readPrice() {
        outView.printInputPrice();
        while (true) {
            Price price = readPriceException();
            if (price != null) {
                return price;
            }
        }
    }

    private Price readPriceException() {
        try {
            return new Price(Integer.parseInt(inputView.inputPrice()));
        } catch (NumberFormatException input) {
            outView.printInvalidNumber();
        } catch (IllegalArgumentException input) {
            outView.printInvalidPrice();
        }
        return null;
    }

    public Price showPrice() {
        Price money = readPrice();
        outView.printPriceValue(money.getValue());
        System.out.println();
        return money;
    }

    public LottoNumbersRepository showLottoNumbers(int ticketCount) {
        outView.printManualTicketCount();
        int manualTicket = Integer.parseInt(inputView.inputManualTicket());
        System.out.println();
        int autoTicket = ticketCount - manualTicket;
        outView.showManualTicketLottoNumber();
        List<String> manualLottos = inputView.inputManualLottos(manualTicket);
        System.out.println();
        LottoNumbersRepository manualRepository = lottoBusiness.createManualLottos(manualLottos);
        LottoNumbersRepository autoRepository = lottoBusiness.createLottos(ticketCount);
        outView.printBuyCount(manualTicket, autoTicket);
        LottoNumbersRepository repository = lottoBusiness.mergeRepository(manualRepository,
                autoRepository);
        outView.printLottos(repository.readLottoNumbersRepository());
        System.out.println();
        return repository;
    }

    public LottoNumbers readLastLotto() {
        outView.printInputLastLotto();
        LottoNumbers lastLotto = lottoBusiness.createInputLotto(inputView.inputLastLotto());
        System.out.println();
        return lastLotto;
    }

    public LottoNumber readBonusBall() {
        outView.printBonusBall();
        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(inputView.inputBonusBall()));
        System.out.println();
        return bonusNumber;
    }

    public void showLotteryStatistics(List<LottoNumbers> allLotteries, List<LottoNumber> lastLotto, int money, LottoNumber bonusBall) {
        Map<MatchResult, Integer> matchCounts = lottoBusiness.countMatchResults(allLotteries, lastLotto, bonusBall);
        String profitRate = lottoBusiness.calculateProfitRrate(matchCounts, money);
        outView.printLotteryStatistics(matchCounts, profitRate);
    }
}
