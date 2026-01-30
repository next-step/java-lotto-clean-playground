package view;

import domain.Money;
import domain.WinningNumbers;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = scanner.nextInt();
        scanner.nextLine();
        return Money.from(amount);
    }

    public WinningNumbers readWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String line = scanner.nextLine();
        return WinningNumbers.of(parseNumbers(line));
    }

    private List<Integer> parseNumbers(String line) {
        return List.of(line.split(","))
                .stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
