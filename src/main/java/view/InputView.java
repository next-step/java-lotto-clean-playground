package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");

        return parseToInt(
                scanner.nextLine(),
                "구입 금액을 입력해야 합니다.",
                "구입 금액은 숫자로 입력해야 합니다."
        );
    }

    public int getManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return parseToInt(
                scanner.nextLine(),
                "수동으로 구매할 로또 개수를 입력해야 합니다.",
                "로또 개수는 숫자로 입력해야 합니다."
        );
    }

    public List<Integer> getManualLottoNumbers() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return parseLottoNumbers(scanner.nextLine());
    }

    public List<Integer> getWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return parseLottoNumbers(scanner.nextLine());
    }

    public int getBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        return parseToInt(
                scanner.nextLine(),
                "보너스 볼을 입력해야 합니다.",
                "보너스 볼은 숫자로 입력해야 합니다."
        );
    }

    private List<Integer> parseLottoNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            numbers.add(parseNumber(token.trim()));
        }
        return numbers;
    }

    private int parseNumber(String token) {
        return parseToInt(
                token,
                "로또 번호를 입력해야 합니다.",
                "로또 번호는 숫자여야 합니다."
        );
    }

    private int parseToInt(
            String input,
            String blankMessage,
            String errorMessage
    ) {
        String trimmedInput = input.trim();
        validateNotBlank(trimmedInput, blankMessage);

        try {
            return Integer.parseInt(trimmedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateNotBlank(String input, String message) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
