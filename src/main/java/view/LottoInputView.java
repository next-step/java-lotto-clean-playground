package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LottoInputView implements LottoView {

    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmountInput = Integer.parseInt(scanner.nextLine());

        printEmptyLine();

        return purchaseAmountInput;
    }

    public int getManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualLottoAmountInput = Integer.parseInt(scanner.nextLine());

        printEmptyLine();

        return manualLottoAmountInput;
    }

    public List<String> getManualLottoNumbers(int manualLottoAmount) {
        if (isNotPositive(manualLottoAmount)) {
            return List.of();
        }
        System.out.println("수동으로 구매할 번호를 입력해 주세요");
        List<String> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoAmount; i++) {
            manualLottoNumbers.add(scanner.nextLine());
        }
        printEmptyLine();

        return Collections.unmodifiableList(manualLottoNumbers);
    }

    public String getWinningLottoString() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoString = scanner.nextLine();

        printEmptyLine();

        return winningLottoString;
    }

    public int getBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBallInput = Integer.parseInt(scanner.nextLine());

        printEmptyLine();

        return bonusBallInput;
    }

    private boolean isNotPositive(int number) {
        return number < 1;
    }

}
