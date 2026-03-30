package controller;

import view.InputView;

import java.util.List;
import java.util.Scanner;

class MockInputView extends InputView {
    boolean getUserCashInputCalled = false;
    boolean getWinningNumbersCalled = false;

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
