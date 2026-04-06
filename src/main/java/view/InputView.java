package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int readManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<List<Integer>> readManualNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            manualNumbers.add(parseLottoNumbers(scanner.nextLine()));
        }
        return manualNumbers;
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseLottoNumbers(input);
    }

    public int readBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private List<Integer> parseLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
