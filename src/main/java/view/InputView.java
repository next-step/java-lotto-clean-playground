package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static int readAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = scanner.nextInt();
        scanner.nextLine();
        return amount;
    }

    public static int readManualPurchase(){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualPurchase = scanner.nextInt();
        scanner.nextLine();
        return manualPurchase;
    }

    public static List<Integer> readManualLotto(){
        String numbers = scanner.nextLine();
        return parseNumbers(numbers);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String numbers = scanner.nextLine();
        return parseNumbers(numbers);
    }

    private static List<Integer> parseNumbers(String rawNumbers) {
        return Arrays.stream(rawNumbers.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int readBonusNumbers() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        scanner.nextLine();
        return bonusNumber;
    }

    public static void close() {
        scanner.close();
    }
}
