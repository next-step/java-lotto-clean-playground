package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final static String PAYMENT_ANOUNCEMENT = "구입금액을 입력해 주세요.";
    private final static String WINNING_ANOUNCEMENT = "지난 주 당첨 번호를 입력해 주세요.";

    public int inputMoney(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(PAYMENT_ANOUNCEMENT);
        final int money = scanner.nextInt();

        return money;
    }

    public List<Integer> inputWinningBall() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(WINNING_ANOUNCEMENT);
        String winningNumber = scanner.nextLine();

        List<Integer> winningNumbers = Arrays.stream(winningNumber.split(",\\s*"))
                .map(Integer::parseInt)
                .filter(n -> 1 <= n)
                .filter(n -> n <= 45)
                .toList();

        return winningNumbers;
    }
}
