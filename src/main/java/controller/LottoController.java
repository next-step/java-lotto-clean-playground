package controller;

import model.Lotto;
import model.LottoGenerator;
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
        OutputView.showLotto(transToLottoDto(lottos), lottos.getBuyLottoCount());

        final Lotto winningLotto = Lotto.from(InputView.inputWinningLotto());
        final LottoResult result = lottos.getResult(winningLotto);
        OutputView.showResult(result.getResult(), result.getRateOfReturn(lottoPurchaseMoney.getValue()));
    }

    private List<List<Integer>> transToLottoDto(final Lottos lottos) {
        return lottos.getLottos().stream()
                .map(Lotto::getNumbers)
                .collect(Collectors.toList());
    }
}
