package view;

import lotto.PurchaseAmount;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseAmount readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        return new PurchaseAmount(SCANNER.nextInt());
    }

}
