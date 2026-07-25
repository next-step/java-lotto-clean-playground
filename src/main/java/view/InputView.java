package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 숫자로 입력해야 합니다.");
        }
    }

    public static List<String> getWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Arrays.asList(scanner.nextLine().split(","));
    }
}
