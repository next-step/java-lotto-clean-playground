package view;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readBuyMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String readWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> readManualLottos(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            manualLottos.add(scanner.nextLine());
        }
        return manualLottos;
    }
}
