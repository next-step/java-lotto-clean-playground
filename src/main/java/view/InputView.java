package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_PRICE = 1_000;

    public static int LottoPurchased() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt() / LOTTO_PRICE;
    }
}
