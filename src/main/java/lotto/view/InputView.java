package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Integer> inputAnswerNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine(), ", ");
        List<Integer> result = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            result.add(number);
        }
        return result;
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }
}
