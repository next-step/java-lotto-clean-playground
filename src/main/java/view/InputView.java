package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getLottoPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    public static int getBonusBall() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine());
    }

    public static int getManualCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine());
    }

    public static List<List<Integer>> getManualNumbers(int manualCount) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            List<Integer> numbers = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
            manualNumbers.add(numbers);
        }
        return manualNumbers;
    }
}
