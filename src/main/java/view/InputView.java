package view;

import domain.Money;
import domain.WinningNumbers;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> numbers = parseNumbers(scanner.nextLine());

        System.out.println("보너스 볼을 입력해 주세요.");
        int bonus = Integer.parseInt(scanner.nextLine().trim());

        return WinningNumbers.of(numbers, bonus);
    }

    private List<Integer> parseNumbers(String line) {
        return Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
