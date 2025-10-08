package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public int getInputMoney() {
        int inputMoney = scanner.nextInt();
        scanner.nextLine();
        return inputMoney;
    }

    public String getInputTargetNumber() {
        String inputTargetNumber = scanner.nextLine().trim();
        return inputTargetNumber;
    }

    public int getInputManualLottoCount() {
        int inputManualLottoCount = scanner.nextInt();
        scanner.nextLine();
        return inputManualLottoCount;
    }

    public List<String> getInputManualLottoNumbers(int manualLottoCount) {
        List<String> inputManualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            String inputManualLottoNumber = scanner.nextLine().trim();
            inputManualLottoNumbers.add(inputManualLottoNumber);
        }
        return inputManualLottoNumbers;
    }

    public int getInputBonusNumber() {
        int bonusNumber = scanner.nextInt();
        scanner.nextLine();
        return bonusNumber;
    }
}
