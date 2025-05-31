package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoInputView {

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    public List<Integer> inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        String winningNumbersString = scanner.nextLine();
        String[] numbers = winningNumbersString.split(",");
        ArrayList<Integer> winningNumbers = new ArrayList<>();

        for (String num : numbers) {
            winningNumbers.add(Integer.parseInt(num.trim()));
        }

        return winningNumbers;
    }
}
