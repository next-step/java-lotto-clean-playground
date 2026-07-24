package view;

import java.io.InputStream;
import java.util.*;

public class InputView {

    private final Scanner scanner;

    public InputView(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public List<Integer> lastWeekWinningNumbers() {
        return validNumbers();
    }

    private List<Integer> validNumbers() {
        try {
            return initNumbers();
        } catch (IllegalArgumentException e) {
            System.out.println("당첨 번호를 입력할 때에는 6개의 숫자여야 하며, 콤마로 구분되어 있어야 합니다.");
        }
        return null;
    }

    private List<Integer> initNumbers() {
        String input = scanner.nextLine();

        if (!input.contains(",")) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }

    public int payment() {
        return validPayment();
    }

    private int validPayment() {
        try {
            return initPayment();
        } catch(InputMismatchException e) {
            System.out.println("지불 금액은 음이 아닌 정수여야 합니다.");
        }
        return -1;
    }

    private int initPayment() {
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (amount <= 0) {
            throw new InputMismatchException();
        }

        return amount;
    }
}
