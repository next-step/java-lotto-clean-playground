package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNIGNUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUSBALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";

    public static int enterLottoPuchase() {
        System.out.println(INPUT_PURCHASE_MESSAGE);
        return Integer.parseInt(scanner.nextLine());
    }

    public static int enterManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> enterManualLottoNumbers(int count) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS);
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            manualNumbers.add(line);
        }
        return manualNumbers;
    }

    public static String enterLottoWinningNumber() {
        System.out.println();
        System.out.println(INPUT_WINNIGNUMBER_MESSAGE);
        return scanner.nextLine();
    }

    public static int enterLottoBonusBall() {
        System.out.println();
        System.out.println(INPUT_BONUSBALL_MESSAGE);
        return Integer.parseInt(scanner.nextLine());
    }
}
