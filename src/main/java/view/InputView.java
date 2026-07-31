package view;

import java.util.Scanner;

public final class InputView {

    private InputView() {

    }

    public static int inputLottoTotalPrice(){
        System.out.println("구입 금액을 입력해 주세요.");
        Scanner lottoScanner = new Scanner(System.in);
        String stringLottoTotalPrice;
        int validLottoTotalPrice;

        try {
            stringLottoTotalPrice = lottoScanner.nextLine();

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("정수로 입력해주세요");
        }

        validLottoTotalPrice = Integer.parseInt(stringLottoTotalPrice);

        return validLottoTotalPrice;
    }

    public static void closeScanner(Scanner scanner) {
        closeScanner(scanner);
    }
}
