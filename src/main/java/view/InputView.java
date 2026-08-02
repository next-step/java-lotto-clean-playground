package view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = Integer.parseInt(scanner.nextLine());
        return purchaseAmount;
    }

    public static int getManualPurchaseAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualPurchaseLottos = Integer.parseInt(scanner.nextLine());
        return manualPurchaseLottos;
    }

    public static String getManualPurchasedLottos() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        String manualPurchasedLotto = scanner.nextLine();
        return manualPurchasedLotto;
    }

    public static String getWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        String enteredWinningNumber = scanner.nextLine();
        return enteredWinningNumber;
    }

    public static int getBounusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(scanner.nextLine());
        return bonusNumber;
    }

}
