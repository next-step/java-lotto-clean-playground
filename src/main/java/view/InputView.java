package view;

import java.util.ArrayList;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        int amount = scanner.nextInt();
        scanner.nextLine();
        return amount;
    }

    public ArrayList<Integer> getWinningNumbers () {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

        String numbers = scanner.nextLine();
        String[] numbersArr = numbers.split(",");
        ArrayList<Integer> winningNumbers = new ArrayList<>();

        for(String number: numbersArr) {
            winningNumbers.add(Integer.parseInt(number.trim()));
        }

        return winningNumbers;
    }
}
