package controller;

import inputView.OutputView;
import inputView.Price;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoNumbersRepository;

import java.util.List;

public class AutoLottoController {
    private final AutoLottoControllerMethod race = new AutoLottoControllerMethod();
    private final OutputView outView = new OutputView();

    public void race() {
        // 1. 구입 금액 입력
        Price price = race.readPrice();
        outView.printPriceValue(price.getValue());
        System.out.println();
        outView.printBuyCount(price.howManyLottos());

        // 2. 로또 번호 생성
        LottoNumbersRepository repository = race.createLottos(price.howManyLottos());
        outView.printLottos(repository.readLottoNumbersRepository());
        System.out.println();

        // 3. 당첨 번호 입력
        LottoNumbers lastLotto = race.createLastLotto();
        System.out.println();

        // // 4. 통계 출력
        showLotteryStatistics(repository.readLottoNumbersRepository(), lastLotto.getNumbers(), price.getValue());
    }

    public void showLotteryStatistics(List<LottoNumbers> allLotteries, List<LottoNumber> lastLotto, int money) {
        int[] matchCounts = race.countMatchResults(allLotteries, lastLotto);
        String profitRate = race.calculateProfitRrate(matchCounts, money);
        outView.printLotteryStatistics(matchCounts, profitRate);
    }
}
