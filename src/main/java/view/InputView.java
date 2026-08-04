package view;

import domain.PurchaseManage;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            int purchaseAmount = Integer.parseInt(scanner.nextLine());
            return purchaseAmount;
        } catch(NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다. 다시 입력해주세요.");
            return getPurchaseAmount();
        }
    }

    public static int getManualPurchaseAmount(int price) {
        int totalCount = price / PurchaseManage.LOTTO_PRICE;
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            int manualPurchaseLottos = Integer.parseInt(scanner.nextLine());
            if (totalCount < manualPurchaseLottos || manualPurchaseLottos < 0) {
                System.out.println("0개 이상" + " " + totalCount + "개 이하로 입력해주세요");
                return getManualPurchaseAmount(price);
            }
            return manualPurchaseLottos;
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다. 다시 입력해주세요.");
            return getManualPurchaseAmount(price);
        }
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

    public static int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(scanner.nextLine());
        return bonusNumber;
    }

}
