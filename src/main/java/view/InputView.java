package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException exception) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }
}
