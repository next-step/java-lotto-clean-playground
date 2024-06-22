package org.duckstudy.controller;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoCount;
import org.duckstudy.model.lotto.LottoNumber;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.Lottos;
import org.duckstudy.view.InputView;
import org.duckstudy.view.OutputView;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        Price price = createPrice();
        Lottos totalLottos = createTotalLottos(price);

        Lotto winningLotto = createWinningLotto();
        LottoNumber bonusNumber = createBonusNumber(winningLotto);

        getWinningResult(price, totalLottos, winningLotto, bonusNumber);
    }

    private Lottos createTotalLottos(Price price) {
        LottoCount totalLottoCount = price.calculateLottoCount();
        LottoCount manualLottoCount = createManualLottoCount(totalLottoCount);
        Lottos manualLottos = createManualLottos(manualLottoCount.getCount());

        LottoCount autoLottoCount = totalLottoCount.subtract(manualLottoCount);
        Lottos autoLottos = Lottos.generateLottos(autoLottoCount.getCount());
        Lottos totalLottos = manualLottos.merge(autoLottos);

        outputView.printLottos(manualLottoCount.getCount(), autoLottoCount.getCount(), totalLottos);
        return totalLottos;
    }

    private Price createPrice() {
        outputView.printInputPrice();
        try {
            Price price = new Price(inputView.inputPrice());
            price.validateInputPrice();
            return price;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createPrice();
        }
    }

    private Lottos createManualLottos(int manualLottoCount) {
        outputView.printInputManualLotto();

        return new Lottos(IntStream.range(0, manualLottoCount)
                .mapToObj(i -> createManualLotto())
                .collect(Collectors.toList()));
    }

    private LottoCount createManualLottoCount(LottoCount lottoCount) {
        outputView.printInputManualLottoCount();
        try {
            LottoCount manualLottoCount = new LottoCount(inputView.inputManualLottoCount());
            manualLottoCount.validateManualLottoCount(lottoCount.getCount());
            return manualLottoCount;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createManualLottoCount(lottoCount);
        }
    }

    private Lotto createManualLotto() {
        try {
            return Lotto.from(inputView.inputManualLotto());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createManualLotto();
        }
    }

    private Lotto createWinningLotto() {
        outputView.printInputWinningLotto();
        try {
            return Lotto.from(inputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createWinningLotto();
        }
    }

    private LottoNumber createBonusNumber(Lotto winningLotto) {
        outputView.printInputBonusNumber();
        try {
            LottoNumber bonusNumber = LottoNumber.valueOf(inputView.inputBonusNumber());
            validateBonusNumber(winningLotto, bonusNumber);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createBonusNumber(winningLotto);
        }
    }

    private void validateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되는 보너스 볼은 입력할 수 없습니다.");
        }
    }

    private void getWinningResult(Price price, Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LottoResult result = createLottoResult(lottos, winningLotto, bonusNumber);
        calculateProfitRate(price, result);
    }

    private LottoResult createLottoResult(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LottoResult result = lottos.accumulateLottoResult(winningLotto, bonusNumber);
        outputView.printLottoResult(result);
        return result;
    }

    private void calculateProfitRate(Price price, LottoResult result) {
        double profitRate = price.calculateProfitRate(result);
        outputView.printTotalProfit(profitRate);
    }
}
