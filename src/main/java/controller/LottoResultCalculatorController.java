package controller;

import model.Lotto;
import model.LottoBatch;
import view.InputView;
import view.OutputView;

import java.nio.file.LinkPermission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResultCalculatorController {
    private final LottoBatch lottoBatch;
    private final InputView inputView;
    private final OutputView outputView;

    LottoResultCalculatorController(LottoBatch lottoBatch, InputView inputView, OutputView outputView) {
        this.lottoBatch = lottoBatch;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void calculate() {
        List<Integer> winningNumbers = inputView.getWinningNumbers();

        
    }
}
