package view.input;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

    @Override
    public String inputLottoMoneyValue() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
