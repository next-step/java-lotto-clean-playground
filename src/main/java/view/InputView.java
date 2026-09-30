package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return parseWinningNumbers(scanner.nextLine());
    }

    private List<Integer> parseWinningNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            numbers.add(parseNumber(token.trim()));
        }
        return numbers;
    }

    private int parseNumber(String token) {
        validateNotBlank(token);

        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }

    private void validateNotBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(
                    "로또 번호를 입력해야 합니다."
            );
        }
    }
}
