package controller.mock;

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
    public int getUserCashInput() {
        getUserCashInputCalled = true;
        return super.getUserCashInput();
    }

    @Override
    public List<Integer> getWinningNumbers() {
        getWinningNumbersCalled = true;
        return super.getWinningNumbers();
    }
}
