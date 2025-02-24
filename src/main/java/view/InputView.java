package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    public int getPurchaseAmount() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("구입금액을 입력해 주세요.");
            int purchaseAmount = scanner.nextInt();
            validatePositive(purchaseAmount);

            return purchaseAmount;
        } catch (InputMismatchException e) {
            System.out.println("올바른 숫자를 입력하세요.");

            return -1;
        }
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("양수가 아닌 음수가 입력되어 예외가 발생되었습니다.");
        }
    }
}
