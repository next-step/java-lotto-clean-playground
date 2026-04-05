package controller.mock;

import controller.LottoResultCalculatorController;
import dto.LottoResultDto;
import model.LottoBatch;
import model.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MockLottoResultCalculatorController extends LottoResultCalculatorController {
    public List<LottoResult> mockMatchCountPerLotto;
    public boolean wrapLottoIntoDtoCalled = false;

    public MockLottoResultCalculatorController(LottoBatch lottoBatch, InputView inputView, OutputView outputView) {
        super(lottoBatch, inputView, outputView);
    }

    @Override
    public List<LottoResult> getMatchCountPerLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.mockMatchCountPerLotto = super.getMatchCountPerLotto(winningNumbers, bonusNumber);
        return this.mockMatchCountPerLotto;
    }

    @Override
    public double getReturnRatio(List<Integer> winningNumbers, int bonusNumber) {
        return super.getReturnRatio(winningNumbers, bonusNumber);
    }

    @Override
    public LottoResultDto wrapLottoIntoDto(List<LottoResult> lottoResults) {
        wrapLottoIntoDtoCalled = true;
        return super.wrapLottoIntoDto(lottoResults);
    }
}
