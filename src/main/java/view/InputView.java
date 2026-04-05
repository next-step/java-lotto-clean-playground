package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        int amount = scanner.nextInt();

        if (amount < 1000) {
            throw new IllegalArgumentException("구입 금액은 천원 이상의 양수값을 입력해주세요!");
        }
        scanner.nextLine();

        return amount;
    }

    public List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

        List<Integer> winningNumbers = new ArrayList<>();

        String numbers = scanner.nextLine();
        String[] numbersArr = numbers.split(",");

        for (String number : numbersArr) {
            if (Integer.parseInt(number) < 0) {
                throw new IllegalArgumentException("당첨번호는 음수일 수 없어요!");
            }
            winningNumbers.add(Integer.parseInt(number.trim()));
        }

        return winningNumbers;
    }
}
