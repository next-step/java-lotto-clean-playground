package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int readBudget() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
    }
}
