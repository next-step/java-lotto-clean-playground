package view;

import java.util.Scanner;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);

    public static String readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = scanner.nextLine();
        System.out.println();
        return money;
    }

    public static int readManualLottoNum() {
        System.out.println("수동으로 구매할 로또수를 입력해 주세요.");
        String manualLottoNum = scanner.nextLine();
        System.out.println();
        return Integer.parseInt(manualLottoNum);
    }

    public static String readManualLotto() {
        String manualLotto = scanner.nextLine();
        return manualLotto;
    }


    public static String readlastWeekLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String manualLotto = scanner.nextLine();
        System.out.println();

        return manualLotto;
    }

    public static int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumber = scanner.nextLine();
        System.out.println();
        return Integer.parseInt(bonusNumber);
    }

}
