package view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int readLottoPrice() {

        int lottoPrice = scanner.nextInt();
        if (lottoPrice < 1000) {
            throw new IllegalArgumentException("로또 구입금액은 1000원이상만 가능합니다.");
        }
        return lottoPrice;
    }

}
