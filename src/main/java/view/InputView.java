package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputPurchaseAmount = scanner.nextLine();
        int purchaseAmount = Integer.parseInt(inputPurchaseAmount);

        System.out.println();

        return purchaseAmount;
    }

    public int readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String inputManualLottoCount = scanner.nextLine();
        int manualLottoCount = Integer.parseInt(inputManualLottoCount);

        System.out.println();

        return manualLottoCount;
    }

    public List<List<Integer>> readManualLottoNumbers(int manualLottoCount) {
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < manualLottoCount; i++) {
            String inputNumbers = scanner.nextLine();
            manualLottoNumbers.add(parseNumbers(inputNumbers));
        }

        System.out.println();

        return manualLottoNumbers;
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = scanner.nextLine();
        System.out.println();

         return parseNumbers(winningNumbers);
    }

    public int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String inputBonusNumber = scanner.nextLine();
        int bonusNumber = Integer.parseInt(inputBonusNumber);

        System.out.println();

        return bonusNumber;
    }

    private List<Integer> parseNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();

        String[] numberStrings = input.split(",");

        for (String numberString : numberStrings) {
            numbers.add(Integer.parseInt(numberString.trim()));
        }

        return numbers;
    }
}
