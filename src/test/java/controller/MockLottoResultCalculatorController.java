package controller;

import model.LottoBatch;
import model.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MockLottoResultCalculatorController extends LottoResultCalculatorController{

    public MockLottoResultCalculatorController(LottoBatch lottoBatch, InputView inputView, OutputView outputView) {
        super(lottoBatch, inputView, outputView);
    }

    @Override
    public List<LottoResult> getMatchCountPerLotto(List<Integer> winningNumbers) {
        return super.getMatchCountPerLotto(winningNumbers);
    }

    @Override
    public double getReturnRatio(List<Integer> winningNumbers) {
        return super.getReturnRatio(winningNumbers);
    }
}
