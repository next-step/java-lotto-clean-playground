package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPurchaseMoney() {
        String input = SCANNER.nextLine().trim();
        InputValidator.validatePurchaseMoneyFormat(input);
        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinningNumber() {
        String winningNumber = SCANNER.nextLine();
        InputValidator.validateInputNumberFormat(winningNumber);
        return parse(winningNumber);
    }

    public static List<Integer> inputManualLottoNumber() {
        String manualLottoNumber = SCANNER.nextLine();
        InputValidator.validateInputNumberFormat(manualLottoNumber);
        return parse(manualLottoNumber);
    }

    public static int inputBonusNumber() {
        String bonusNumber = SCANNER.nextLine().trim();
        InputValidator.validateBonusNumberFormat(bonusNumber);
        return Integer.parseInt(bonusNumber);
    }

    public static int inputManualLottoNumberTrialCount() {
        String count = SCANNER.nextLine().trim();
        InputValidator.validateManualLottoNumberTrialCount(count);
        return Integer.parseInt(count);
    }

    private static List<Integer> parse(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}