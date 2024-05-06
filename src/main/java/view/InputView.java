package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final static int UNIT_MONEY = 1000;
    private final String PAYMENT_ANOUNCEMENT = "구입금액을 입력해 주세요.";
    private final String WINNING_ANOUNCEMENT = "지난 주 당첨 번호를 입력해 주세요.";

    public int readMoney(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(PAYMENT_ANOUNCEMENT);
        final int money = scanner.nextInt();
        try {
            validateMoneyUnit(money);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return readMoney();
        }
        return money;
    }

    public List<Integer> readWinningNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(WINNING_ANOUNCEMENT);
        String winningNumber = scanner.nextLine();
        List<Integer> winningNumbers;

        try {
            winningNumbers = Arrays.stream(winningNumber.split(",\\s*"))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            System.out.println("로또 당첨 번호는 다음과 같은 문자 Ex. 1, 2, 3, 4, 5, 6 형태여야 합니다.");
            return readWinningNumber();
        }

        return winningNumbers;
    }

    public void validateMoneyUnit(int money) {
        if(money % UNIT_MONEY != 0){
            throw new NumberFormatException("입력되는 돈은 1000원 단위여야 합니다.");
        }
    }
}
