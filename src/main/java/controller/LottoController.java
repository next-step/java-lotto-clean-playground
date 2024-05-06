package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    public void run() {
        int purchaseAmount = InputView.getPurchaseAmount();
        List<Lotto> lottos = LottoMachine.generateLottos(purchaseAmount / Lotto.LOTTO_PRICE);
        OutputView.printLottos(lottos);

        Lotto winningLotto = InputView.getWinningLotto();
        LottoNumber bonusNumber = InputView.getBonusNumber();

        LottoResult result = getLottoResult(lottos, winningLotto, bonusNumber);
        OutputView.printResult(result, purchaseAmount);
    }

    private LottoResult getLottoResult(List<Lotto> lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        List<LottoPrize> prizes = lottos.stream()
                .map(lotto -> getLottoPrize(lotto, winningLotto, bonusNumber))
                .toList();
        return new LottoResult(prizes);
    }

    private LottoPrize getLottoPrize(Lotto lotto, Lotto winningLotto, LottoNumber bonusNumber) {
        int matchCount = countMatchingNumbers(lotto, winningLotto);
        boolean isMatchBonus = lotto.contains(bonusNumber);
        return LottoPrize.matchPrize(matchCount, isMatchBonus);
    }

    private int countMatchingNumbers(Lotto lotto, Lotto winningLotto) {
        return (int) lotto.numbers().stream()
                .filter(winningLotto.numbers()::contains)
                .count();
    }
}
