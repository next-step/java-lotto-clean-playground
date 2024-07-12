package controller;

import model.Lotto;
import model.LottoGenerator;
import model.LottoNumber;
import model.LottoPurchaseMoney;
import model.LottoResult;
import model.Lottos;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public LottoController() {
    }

    public void run() {
        final LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(InputView.inputMoney());
        final LottoGenerator lottoGenerator = new LottoGenerator();

        final Lottos lottos = lottoGenerator.generateRandomLotto(lottoPurchaseMoney);
        OutputView.showLotto(transToLottosDto(lottos), lottos.getBuyLottoCount());

        final Lotto winningLotto = Lotto.fromStringsInput(InputView.inputWinningLotto());
        final LottoNumber bonusNumber = LottoNumber.from(InputView.inputBonusNumber());

        final LottoResult result = lottos.getResult(winningLotto, bonusNumber);
        OutputView.showResult(result.getResult(), result.getRateOfReturn(lottoPurchaseMoney.getValue()));
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
