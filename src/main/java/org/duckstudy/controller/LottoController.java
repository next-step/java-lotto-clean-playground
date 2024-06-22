package org.duckstudy.controller;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.Lotto;
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
        int totalLottoCount = price.calculateLottoCount();

        int manualLottoCount = getManualLottoCount(totalLottoCount);
        Lottos manualLottos = createManualLottos(manualLottoCount);

        int autoLottoCount = totalLottoCount - manualLottoCount;
        Lottos autoLottos = Lottos.generateLottos(autoLottoCount);

        Lottos totalLottos = manualLottos.merge(autoLottos);
        outputView.printLottos(manualLottoCount, autoLottoCount, totalLottos);

        Lotto winningLotto = createWinningLotto();
        LottoNumber bonusNumber = createBonusNumber(winningLotto);

        getWinningResult(price, totalLottos, winningLotto, bonusNumber);
    }

    private Price createPrice() {
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

    private int getManualLottoCount(int lottoCount) {
        try {
            int manualLottoCount = inputView.inputManualLottoCount();
            validateManualLottoCount(lottoCount, manualLottoCount);
            return manualLottoCount;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return getManualLottoCount(lottoCount);
        }
    }

    private void validateManualLottoCount(int lottoCount, int manualLottoCount) {
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException("음수로 입력할 수 없습니다.\n");
        }
        if (manualLottoCount > lottoCount) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수가 전체 로또 수를 초과합니다.\n");
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
        try {
            return Lotto.from(inputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createWinningLotto();
        }
    }

    private LottoNumber createBonusNumber(Lotto winningLotto) {
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
