package view;

import domain.InputParser;
import domain.Lotto;
import domain.LottoMachine;
import domain.LottoNumber;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        validateEmpty(input);
        validateNumber(input);
        return Integer.parseInt(input);
    }

    public Lotto getWinnerNumbers() {
        System.out.println("\n지난 주 당첨번호를 입력해 주세요.");
        String input = scanner.nextLine();
        validateEmpty(input);
        validateLotto(input);
        return InputParser.parseToLotto(input);
    }

    public LottoNumber getBonusNumber(Lotto winnerNumbers) {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        validateEmpty(input);
        validateNumber(input);
        Lotto.validateBonusNumber(winnerNumbers, InputParser.parseToLottoNumber(input));
        return InputParser.parseToLottoNumber(input);
    }

    public int getManualCount(int count) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        validateEmpty(input);
        validateNumber(input);
        LottoMachine.validateManualCount(count, Integer.parseInt(input));
        return Integer.parseInt(input);
    }

    public Lotto getManualLotto() {
        String input = scanner.nextLine();
        validateEmpty(input);
        validateLotto(input);
        return InputParser.parseToLotto(input);
    }

    public static void validateNumber(String input) {
        if (isNotNumeric(input)) {
            throw new IllegalArgumentException("입력값은 숫자여야 합니다.");
        }
    }

    public static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
    }

    public static void validateLotto(String input) {
        String[] tokens = input.split(",");
        for (String token : tokens) {
            if (isNotNumeric(token.trim())) {
                throw new IllegalArgumentException("로또 번호는 숫자여야 하며, 각 번호는 쉼표(,)로 구분되어야 합니다.");
            }
        }
    }

    private static boolean isNotNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

}
