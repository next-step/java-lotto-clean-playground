package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInt(input);
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseNumbers(input, "당첨 번호는 쉼표로 구분된 숫자여야 합니다.");
    }

    public int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInt(input);
    }

    public int readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInt(input);
    }

    public List<List<Integer>> readManualLottoNumbers(int manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<List<Integer>> manualLottoNumbers = new ArrayList<>();

        for (int i = 0; i < manualLottoCount; i++) {
            String input = scanner.nextLine();
            List<Integer> lottoNumbers = parseNumbers(input, "수동 구매 번호는 쉼표로 구분된 숫자여야 합니다.");
            manualLottoNumbers.add(lottoNumbers);
        }

        return manualLottoNumbers;
    }

    private List<Integer> parseNumbers(String input, String errorMessage) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }
}
