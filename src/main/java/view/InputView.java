package view;

import domain.InputParser;
import domain.Lotto;
import domain.LottoNumber;
import domain.ValidateInput;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateNumber(input);
        return Integer.parseInt(input);
    }

    public Lotto getWinnerNumbers() {
        System.out.println("\n지난 주 당첨번호를 입력해 주세요.");
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateLotto(input);
        return InputParser.parseToLotto(input);
    }

    public LottoNumber getBonusNumber(Lotto winnerNumbers) {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateNumber(input);
        ValidateInput.validateBonusNumber(winnerNumbers, InputParser.parseToLottoNumber(input));
        return InputParser.parseToLottoNumber(input);
    }

    public int getManualCount(int count) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateNumber(input);
        ValidateInput.validateManualCount(count, Integer.parseInt(input));
        return Integer.parseInt(input);
    }

    public Lotto getManualLotto() {
        String input = scanner.nextLine();
        ValidateInput.validateEmpty(input);
        ValidateInput.validateLotto(input);
        return InputParser.parseToLotto(input);
    }


}
