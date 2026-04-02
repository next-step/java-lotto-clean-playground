package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        while(true) {
            System.out.println("구입 금액을 입력해 주세요.");
            int amount = scanner.nextInt();

            if(amount >= 1000) {
                scanner.nextLine();
                return amount;
            }
            System.out.println("구입 가격은 1000원 이상의 양수로 입력해주세요!");
        }
    }

    public List<Integer> getWinningNumbers () {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

        List<Integer> winningNumbers = new ArrayList<>();

        try {
            String numbers = scanner.nextLine();
            String[] numbersArr = numbers.split(",");

            for (String number : numbersArr) {
                winningNumbers.add(Integer.parseInt(number.trim()));
            }

        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요!");
            System.exit(-1);
        }



        return winningNumbers;
    }
}
