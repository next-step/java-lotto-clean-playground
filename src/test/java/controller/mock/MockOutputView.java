package controller.mock;

import dto.LottoDto;
import dto.LottoResultDto;
import view.OutputView;

import java.util.List;

public class MockOutputView extends OutputView {
    public boolean printPurchaseResultCalled = false;
    public boolean printStatsCalled = false;
    public boolean printReturnRatioCalled = false;

    @Override
    public void printPurchaseResult(List<LottoDto> lottoDtoList) {
        printPurchaseResultCalled = true;
        super.printPurchaseResult(lottoDtoList);
    }

    @Override
    public void printStats(LottoResultDto matchCountPerLotto) {
        printStatsCalled = true;
        super.printStats(matchCountPerLotto);
    }

    @Override
    public void printReturnRatio(double returnRatio) {
        printReturnRatioCalled = true;
        super.printReturnRatio(returnRatio);
    }
}
