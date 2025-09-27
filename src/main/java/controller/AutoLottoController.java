package controller;

import inputView.OutputView;
import inputView.Price;
import model.LottoNumbers;
import model.LottoNumbersRepository;

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
        race.createLotteryStatistics(repository.readLottoNumbersRepository(), lastLotto.getNumbers(), price.getValue());
    }
}
