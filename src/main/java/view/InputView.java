package view;

import domain.Money;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Money.from(scanner.nextInt());
    }
}
