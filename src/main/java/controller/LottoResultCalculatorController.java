package controller;

import constants.LottoSettingsConstants;
import model.Lotto;
import model.LottoBatch;
import model.LottoResult;
import util.ValidateLotto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoResultCalculatorController {
    private final LottoBatch lottoBatch;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoResultCalculatorController(LottoBatch lottoBatch, InputView inputView, OutputView outputView) {
        this.lottoBatch = lottoBatch;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void calculate() {
        List<Integer> winningNumbers = this.inputView.getWinningNumbers();

        List<LottoResult> matchCountPerLotto = this.getMatchCountPerLotto(winningNumbers);
        this.outputView.printStats(matchCountPerLotto);
        this.outputView.printReturnRatio(getReturnRatio(winningNumbers));
    }

    protected double getReturnRatio(List<Integer> winningNumbers) {
        List<LottoResult> result = this.getMatchCountPerLotto(winningNumbers);

        double earnResult = 0.0;
        for (LottoResult lottoResult : result) {
            earnResult += lottoResult.reward;
        }

        return earnResult / (LottoSettingsConstants.LOTTO_PRICE * lottoBatch.getLottoCount());
    }

    protected List<LottoResult> getMatchCountPerLotto(List<Integer> winningNumbers) {
        ValidateLotto.checkIfNumbersAreValid(winningNumbers);
        List<LottoResult> result = new ArrayList<>();

        for (Lotto lotto : lottoBatch.getAllLotto()) {
            result.add(lotto.calculateLottoResult(winningNumbers));
        }

        return result;
    }
}
