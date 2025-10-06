package view;

import java.util.Scanner;

public class InputView {

    Scanner scanner = new Scanner(System.in);

    public int inputMoney() {
        int money = scanner.nextInt();
        scanner.nextLine();
        return money;
    }

    public String inputLottoAnswer() {
        String lottoAnswer = scanner.nextLine();
        return lottoAnswer;
    }

    public int inputBonusNumber() {
        int bonusNumber = scanner.nextInt();
        scanner.nextLine();
        return bonusNumber;
    }

    public int inputManualCount() {
        int manualCount = scanner.nextInt();
        scanner.nextLine();
        return manualCount;
    }

    public String inputManualNumbers() {
        String manualNumbers = scanner.nextLine();
        return manualNumbers;
    }

}
