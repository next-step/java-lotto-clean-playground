package view;

import domain.Lotto;
import domain.WinningNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int readLottoPrice() {
        int lottoPrice = scanner.nextInt();
        scanner.nextLine();
        if (lottoPrice < 1000) {
            throw new IllegalArgumentException("로또 구입금액은 1000원이상만 가능합니다.");
        }
        return lottoPrice;
    }

    public static List<List<Integer>> inputManualNumbers(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(inputSingleManualNumbers());
        }
        return manualNumbers;
    }
    public static int inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualCount = scanner.nextInt();
        scanner.nextLine();
        return manualCount;
    }

    private static List<Integer> inputSingleManualNumbers() {
        String input = scanner.nextLine().trim();
        return parseNumbers(input);
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBall = scanner.nextInt();
        scanner.nextLine(); // 개행문자 처리
        return bonusBall;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return List.of();
        }

        return parseNumbers(input);
    }

    private static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
