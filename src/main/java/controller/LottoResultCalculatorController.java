package controller;

import constants.LottoSettingsConstants;
import constants.ScriptConstants;
import dto.LottoResultDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoFinanceStatsCalculator;
import model.LottoResult;
import model.WinCondition;
import view.InputView;
import view.OutputView;

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
        LottoFinanceStatsCalculator lottoFinanceStatsCalculator = new LottoFinanceStatsCalculator(this.lottoBatch);
        WinCondition winCondition = this.acceptWinCondition();

        this.outputView.printAllStats(wrapLottoIntoDto(lottoFinanceStatsCalculator.getLottoResults(winCondition)));
        this.outputView.printReturnRatio(lottoFinanceStatsCalculator.getReturnRatio(winCondition));
    }

    protected WinCondition acceptWinCondition() {
        List<Integer> winningNumbers = this.inputView.getWinningNumbers();
        int bonusNumber = this.inputView.getSingleIntegerFromUserAfterShowingAScript(ScriptConstants.INPUT_ENTER_BONUS_NUMBER_SCRIPT);

        return new WinCondition(new Lotto((winningNumbers)), bonusNumber);
    }

    protected LottoResultDto wrapLottoIntoDto (List<LottoResult> lottoResults) {
        Map<LottoResult, Integer> result = new LinkedHashMap<>();
        for (LottoResult winningLotto: LottoSettingsConstants.WINNING_LOTTO_RESULT_ASCENDING_ORDER) {
            result.put(winningLotto, 0);
        }

        for (LottoResult lottoResult : lottoResults) {
            if(result.get(lottoResult) != null) {
                result.put(lottoResult, result.get(lottoResult) + 1);
            }
        }

        return new LottoResultDto(result);
    }
}
