package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = scanner.nextInt();
        scanner.close();

        return purchaseAmount;
    }
}
