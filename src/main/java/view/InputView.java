package view;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static int readAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = scanner.nextInt();
        scanner.nextLine();
        return amount;
    }

    public static int readManualPurchase(){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualPurchase = scanner.nextInt();
        scanner.nextLine();
        return manualPurchase;
    }

    public static String readManualLotto(){
        return scanner.nextLine();
    }

    public static String readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int readBonusNumbers() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        scanner.nextLine();
        return bonusNumber;
    }

    public static void close() {
        scanner.close();
    }
}
