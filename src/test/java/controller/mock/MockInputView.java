package controller.mock;

import constants.ScriptConstants;
import view.InputView;

import java.util.List;
import java.util.Scanner;

public class MockInputView extends InputView {
    public boolean getUserCashInputCalled = false;
    public boolean getWinningNumbersCalled = false;

    public MockInputView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public int getSingleIntegerFromUserAfterShowingAScript(String message) {
        getUserCashInputCalled = true;
        return super.getSingleIntegerFromUserAfterShowingAScript(ScriptConstants.INPUT_CASH_SCRIPT);
    }

    @Override
    public List<Integer> getWinningNumbers() {
        getWinningNumbersCalled = true;
        return super.getWinningNumbers();
    }
}
