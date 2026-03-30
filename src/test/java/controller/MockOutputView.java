package controller;

import dto.LottoDto;
import view.OutputView;

import java.util.List;

class MockOutputView extends OutputView {
    boolean printPurchaseResultCalled = false;
    boolean printStatsCalled = false;
    boolean printReturnRatioCalled = false;

    @Override
    public void printPurchaseResult(List<LottoDto> lottoDtoList) {
        printPurchaseResultCalled = true;
        super.printPurchaseResult(lottoDtoList);
    }

    @Override
    public void printStats(List<Integer> matchCountPerLotto) {
        printStatsCalled = true;
        super.printStats(matchCountPerLotto);
    }

    @Override
    public void printReturnRatio(double returnRatio) {
        printReturnRatioCalled = true;
        super.printReturnRatio(returnRatio);
    }
}
