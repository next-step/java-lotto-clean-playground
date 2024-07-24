package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ", ";

    public static int inputMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요");
        return sc.nextInt();
    }

    public static String[] inputWinningLotto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        final String input = sc.nextLine();
        return input.split(DELIMITER);
    }

    public static String inputBonusNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n보너스 볼을 입력해 주세요.");
        return sc.nextLine();
    }

    public static String inputManualLottoCnt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return sc.nextLine();
    }

    public static List<String[]> inputManualLotto(final int count) {
        List<String[]> inputs = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < count; i++) {
            inputs.add(sc.nextLine().split(DELIMITER));
        }

        return inputs;
    }
}
