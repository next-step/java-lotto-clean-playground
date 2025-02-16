package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println();
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputManualLottoCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<List<Integer>> inputManualLottoNumbers(int manualCount) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, manualCount)
            .mapToObj(i -> splitNumbers(scanner.nextLine()))
            .toList();
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return splitNumbers(scanner.nextLine());
    }

    public static int inputBonusNumber() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private static List<Integer> splitNumbers(String numbers) {
        return Arrays.stream(numbers.split(", "))
            .map(Integer::parseInt)
            .toList();
    }
}
