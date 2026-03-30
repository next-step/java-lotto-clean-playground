package controller;

import model.LottoBatch;
import view.InputView;
import view.OutputView;

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

        List<Integer> matchCountPerLotto = this.lottoBatch.getMatchCountPerLotto(winningNumbers);
        this.outputView.printStats(matchCountPerLotto);
        this.outputView.printReturnRatio(lottoBatch.getReturnRatio(winningNumbers));
    }

}
