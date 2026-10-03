package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }
    public String getPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public String getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public String getManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
        return scanner.nextLine();
    }

    public void printManualNumbersGuide() {
        System.out.println("수동으로 구매할 번호를 입력해주세요.");
    }

    public String getManualNumbers() {
        return scanner.nextLine();
    }
}
