package view;

import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    public String readLottoPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }
}