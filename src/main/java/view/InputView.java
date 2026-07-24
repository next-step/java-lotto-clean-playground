package view;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
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

        if (amount <= 0) {
            throw new InputMismatchException();
        }

        return amount;
    }
}
