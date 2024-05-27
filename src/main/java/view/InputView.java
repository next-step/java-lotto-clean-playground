package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;
    private static final String COMMA = ",";
    private static final int INITIAL_NUMBER = 0;
    private int count = 0;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getMoney() {
        System.out.println("금액을 입력하세요:");
        return scanner.nextInt();
    }

    public List<Integer> getWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력하시오 (쉼표로 구분)");
        String winNumbers = scanner.next();
        return Arrays.stream(winNumbers.split(COMMA))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int bonusNumber() {
        System.out.println("보너스 볼을 입력하세요:");
        return scanner.nextInt();
    }

    public int getPassiveCount() {
        System.out.println("수동으로 구매할 로또 수를 입력하시오: ");
        return count += scanner.nextInt();
    }

    public List<List<Integer>> getPassiveNumbers(int count) {
        System.out.println("수동으로 구매할 로또 번호를 입력하세요 (쉼표로 구분):");
        List<List<Integer>> passiveNumbers = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < count; i++) {
            String numbers = scanner.next();
            passiveNumbers.add(Arrays.stream(numbers.split(COMMA))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }
        return passiveNumbers;
    }
}
