package controller;

import inputView.InputView;
import inputView.OutputView;
import inputView.Price;
import model.LottoBusiness;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoNumbersRepository;

import java.util.List;

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
        outView.printBuyCount(money.howManyLottos());
        return money;
    }

    public LottoNumbersRepository showLottoNumbers(int ticketCount) {
        LottoNumbersRepository repository = lottoBusiness.createLottos(ticketCount);
        outView.printLottos(repository.readLottoNumbersRepository());
        System.out.println();
        return repository;
    }

    public LottoNumbers readLastLotto() {
        outView.printInputLastLotto();
        LottoNumbers lastLotto = lottoBusiness.createLastLotto(inputView.inputLastLotto());
        System.out.println();
        return lastLotto;
    }

    public void showLotteryStatistics(List<LottoNumbers> allLotteries, List<LottoNumber> lastLotto, int money) {
        int[] matchCounts = lottoBusiness.countMatchResults(allLotteries, lastLotto);
        String profitRate = lottoBusiness.calculateProfitRrate(matchCounts, money);
        outView.printLotteryStatistics(matchCounts, profitRate);
    }
}
