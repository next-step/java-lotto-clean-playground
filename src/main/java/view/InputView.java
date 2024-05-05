package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final static int UNIT_MONEY = 1000;
    private final String PAYMENT_ANOUNCEMENT = "구입금액을 입력해 주세요.";
    private final String WINNING_ANOUNCEMENT = "지난 주 당첨 번호를 입력해 주세요.";

    public int inputMoney(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(PAYMENT_ANOUNCEMENT);
        final int money = scanner.nextInt();
        validateMinMoney(money);
        validateMoneyUnit(money);
        return money;
    }

    public List<Integer> inputWinningBall() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(WINNING_ANOUNCEMENT);
        String winningNumber = scanner.nextLine();

        List<Integer> winningNumbers = Arrays.stream(winningNumber.split(",\\s*"))
                .map(Integer::parseInt)
                .filter(num -> validateWinningNumber(num))
                .toList();

        return winningNumbers;
    }

    public void validateMoneyUnit(int money) {
        if(money % UNIT_MONEY != 0){
            throw new IllegalArgumentException("입력되는 돈은 1000원 단위여야 합니다.");
        }
    }

    public void validateMinMoney(int money) {
        if(money < UNIT_MONEY) {
            throw new IllegalArgumentException("입력되는 돈은 최소 1000원 입니다.");
        }
    }

    public boolean validateWinningNumber(int num) {
        if(1 <= num && num <= 45){
            return true;
        }
        return false;
    }
}
