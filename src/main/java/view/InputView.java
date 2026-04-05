package view;

import domain.Lotto;
import domain.ParseToLotto;
import domain.ValidateInput;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateMoney(input);
        return Integer.parseInt(input);
    }

    public Lotto getWinnerNumbers() {
        System.out.println("\n지난 주 당첨번호를 입력해 주세요.");
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateLotto(input);
        return ParseToLotto.parseToLotto(input);
    }


}
