package controller;

import view.InputView;

import java.util.Scanner;

class MockInputView extends InputView {
    boolean getUserCashInputCalled = false;
    int returnValue = 0;

    public MockInputView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public int getUserCashInput() {
        getUserCashInputCalled = true;
        return super.getUserCashInput();
    }
}
