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

    public List<Integer> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = scanner.nextLine();
        System.out.println();

        String[] splitWinningNumbers = inputWinningNumbers.split(",");

        List<Integer> winningNumbers = new ArrayList<>();

        for (String splitWinningNumber : splitWinningNumbers) {
            splitWinningNumber = splitWinningNumber.trim();
            winningNumbers.add(Integer.parseInt(splitWinningNumber));
        }

         return winningNumbers;
    }

    public int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String inputBonusNumber = scanner.nextLine();
        int bonusNumber = Integer.parseInt(inputBonusNumber);

        System.out.println();

        return bonusNumber;
    }
}
