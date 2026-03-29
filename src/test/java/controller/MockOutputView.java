package controller;

import dto.LottoDto;
import view.OutputView;

import java.util.List;

class MockOutputView extends OutputView {
    boolean printPurchaseResultCalled = false;

    @Override
    public void printPurchaseResult(List<LottoDto> lottoDtoList) {
        printPurchaseResultCalled = true;
        super.printPurchaseResult(lottoDtoList);
    }
}
