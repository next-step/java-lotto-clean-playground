package controller.mock;

import controller.LottoResultCalculatorController;
import model.LottoBatch;
import model.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MockLottoResultCalculatorController extends LottoResultCalculatorController {
    public List<LottoResult> mockMatchCountPerLotto;

    public MockLottoResultCalculatorController(LottoBatch lottoBatch, InputView inputView, OutputView outputView) {
        super(lottoBatch, inputView, outputView);
    }

    @Override
    public List<LottoResult> getMatchCountPerLotto(List<Integer> winningNumbers) {
        this.mockMatchCountPerLotto = super.getMatchCountPerLotto(winningNumbers);
        return this.mockMatchCountPerLotto;
    }

    @Override
    public double getReturnRatio(List<Integer> winningNumbers) {
        return super.getReturnRatio(winningNumbers);
    }
}
