package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String PAYMENT_ANOUNCEMENT = "구입금액을 입력해 주세요.";
    private final String WINNING_ANOUNCEMENT = "지난 주 당첨 번호를 입력해 주세요.";
    private final String BONUSBALL_ANOUNCEMENT = "보너스 볼을 입력해 주세요.";
    private final Scanner SCANNER = new Scanner(System.in);

    public String readMoney(){
        System.out.println(PAYMENT_ANOUNCEMENT);
        return SCANNER.nextLine();
    }

    public List<Integer> readWinningNumber() {
        System.out.println(WINNING_ANOUNCEMENT);
        String winningNumber = SCANNER.nextLine();
        return Arrays.stream(winningNumber.split(",\\s*"))
                .map(Integer::parseInt)
                .toList();
    }

    public int readBonusBall() {
        System.out.println(BONUSBALL_ANOUNCEMENT);
        return SCANNER.nextInt();
    }
}
