package controller.mock;

import constants.ScriptConstants;
import view.InputView;

import java.util.List;
import java.util.Scanner;

public class MockInputView extends InputView {
    public int getSingleIntegerFromUserAfterShowingAScriptCalledCount = 0;
    public boolean getManuallyPurchasedLottoNumbers = false;
    public boolean getWinningNumbersCalled = false;

    public MockInputView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public int getSingleIntegerFromUserAfterShowingAScript(String message) {
        getSingleIntegerFromUserAfterShowingAScriptCalledCount += 1;
        return super.getSingleIntegerFromUserAfterShowingAScript(ScriptConstants.INPUT_CASH_SCRIPT);
    }

    @Override
    public List<Integer> getWinningNumbers() {
        getWinningNumbersCalled = true;
        return super.getWinningNumbers();
    }

    @Override
    public List<List<Integer>> getManuallyPurchasedLottoNumbers(int count) {
        this.getManuallyPurchasedLottoNumbers = true;
        return super.getManuallyPurchasedLottoNumbers(count);
    }
}
