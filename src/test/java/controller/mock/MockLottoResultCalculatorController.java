package controller.mock;

import controller.LottoResultCalculatorController;
import dto.LottoResultDto;
import model.LottoBatch;
import model.LottoResult;
import model.WinCondition;
import view.InputView;
import view.OutputView;

import javax.swing.*;
import java.util.List;

public class MockLottoResultCalculatorController extends LottoResultCalculatorController {
    public boolean wrapLottoIntoDtoCalled = false;

    public MockLottoResultCalculatorController(LottoBatch lottoBatch, InputView inputView, OutputView outputView) {
        super(lottoBatch, inputView, outputView);
    }

    @Override
    public LottoResultDto wrapLottoIntoDto(List<LottoResult> lottoResults) {
        wrapLottoIntoDtoCalled = true;
        return super.wrapLottoIntoDto(lottoResults);
    }
}
