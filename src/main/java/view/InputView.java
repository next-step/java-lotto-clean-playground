package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.(1000원 단위)");
        return scanner.nextLine();
    }

    public static String inputManualLottoCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> inputManualLottoNumbers(final int count) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            manualLottos.add(scanner.nextLine());
        }
        return manualLottos;
    }

    public static String inputWinningNumberForLastWeek() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해  주세요.");
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
