package controller;

import constants.LottoSettingsConstants;
import dto.LottoResultDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoResult;
import common.ValidateLotto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        this.outputView.printStats(wrapLottoIntoDto(matchCountPerLotto));
        this.outputView.printReturnRatio(getReturnRatio(winningNumbers));
    }

    protected double getReturnRatio(List<Integer> winningNumbers) {
        List<LottoResult> result = this.getMatchCountPerLotto(winningNumbers);

        double earnResult = 0.0;
        for (LottoResult lottoResult : result) {
            earnResult += lottoResult.getReward();
        }

        return earnResult / (LottoSettingsConstants.LOTTO_PRICE * lottoBatch.getLottoCount());
    }

    protected List<LottoResult> getMatchCountPerLotto(List<Integer> winningNumbers) {
        ValidateLotto.checkIfNumbersAreValid(winningNumbers);
        List<LottoResult> result = new ArrayList<>();

        for (Lotto lotto : lottoBatch.getAllLotto()) {
            result.add(lotto.compareWithWinningNumbers(winningNumbers));
        }

        return result;
    }

    protected LottoResultDto wrapLottoIntoDto (List<LottoResult> lottoResults) {
        Map<LottoResult, Integer> result = new LinkedHashMap<>();
        for (LottoResult winningLotto: LottoSettingsConstants.WINNING_LOTTO_RESULT_ASCENDING_ORDER) {
            result.put(winningLotto, 0);
        }

        for (LottoResult lottoResult : lottoResults) {
            result.put(lottoResult, result.get(lottoResult) + 1);
        }

        return new LottoResultDto(result);
    }
}
