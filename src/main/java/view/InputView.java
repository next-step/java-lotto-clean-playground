package view;

import domain.Lotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("구입금액이 비어있습니다.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 숫자여야 합니다.");
        }
    }

    public Lotto getWinnerNumbers() {
        System.out.println("\n지난 주 당첨번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseToLotto(input);
    }

    public Lotto parseToLotto(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
        String[] tokens = input.split(", ");
        List<LottoNumber> winningNumbers = new ArrayList<>();
        try {
            for (String token : tokens) {
                int number = Integer.parseInt(token.trim());
                winningNumbers.add(new LottoNumber(number));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 하며, 구분자는 ', '여야 합니다.");
        }

        return new Lotto(winningNumbers);
    }


}
