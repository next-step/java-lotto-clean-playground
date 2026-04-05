package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPurchaseMoney() {
        String input = SCANNER.nextLine().trim();
        validatePurchaseMoneyFormat(input); // 파싱 전 String 상태로 형식 검증
        return parseInt(input);
    }

    public static List<Integer> inputWinningNumber() {
        String winningNumber = SCANNER.nextLine();
        validateWinningNumberFormat(winningNumber); // 파싱 전 String 상태로 형식 검증
        return parse(winningNumber);
    }

    private static List<Integer> parse(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }



    public static int inputBounusNumber() {
        String bonusNumber = SCANNER.nextLine();
        validateBonusNumberFormat(bonusNumber); // 파싱 전 String 상태로 형식 검증
        return parseInt(bonusNumber);
    }

    private static void validatePurchaseMoneyFormat(String input) {
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자만 입력해야 합니다.");
        }
    }

    private static void validateWinningNumberFormat(String input) {
        // 숫자, 쉼표, 공백 외의 문자가 포함되어 있는지 정규표현식으로 검사
        if (!input.matches("^[0-9,\\s]+$")) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자, 공백, 쉼표(,)만 포함할 수 있습니다.");
        }
    }
    private static void validateBonusNumberFormat(String input){
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자만 있어야합니다..");
        }
    }
}
