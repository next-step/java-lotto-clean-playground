package view;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public int inputMoney() {
        int money = scanner.nextInt();
        return money;
    }

    public String inputLottoAnswer() {
        scanner.nextLine();
        String lottoAnswer = scanner.nextLine();
        return lottoAnswer;
    }

    public int inputBonusNumber() {
        int bonusNumber = scanner.nextInt();
        return bonusNumber;
    }
}
