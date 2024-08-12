package controller;

import model.BonusNumber;
import model.Lotto;
import model.LottoNumber;
import model.LottoPurchaseMoney;
import model.LottoResult;
import model.Lottos;
import model.ManualBuyCount;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public void run() {
        final LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(InputView.inputMoney());
        final Lottos lottos = generateLottos(lottoPurchaseMoney);

        final Lotto winningLotto = Lotto.fromStringsInput(InputView.inputWinningLotto());
        final LottoNumber bonusNumber = BonusNumber.of(InputView.inputBonusNumber(), transToLottoDto(winningLotto));

        final LottoResult result = lottos.getResult(winningLotto, bonusNumber);
        OutputView.showResult(result.getResult(), result.getRateOfReturn(lottoPurchaseMoney.getValue()));
    }

    private Lottos generateLottos(final LottoPurchaseMoney lottoPurchaseMoney) {
        final ManualBuyCount manualBuyCount = ManualBuyCount.of(InputView.inputManualLottoCnt(), lottoPurchaseMoney);
        final Lottos manualLotto = Lottos.forManualInput(InputView.inputManualLotto(manualBuyCount.getCount()));
        final Lottos autoLotto = Lottos.forRandomGenerate(lottoPurchaseMoney, manualBuyCount);

        final Lottos lottos = new Lottos(manualLotto.getLottos(), autoLotto.getLottos());
        OutputView.showLotto(transToLottosDto(lottos), manualLotto.getBuyLottoCount(), autoLotto.getBuyLottoCount());
        return lottos;
    }

    private List<List<Integer>> transToLottosDto(final Lottos lottos) {
        return lottos.getLottos().stream()
            .map(this::transToLottoDto)
            .collect(Collectors.toList());
    }

    private List<Integer> transToLottoDto(final Lotto lotto) {
        return lotto.getNumbers().stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList());
    }
}
