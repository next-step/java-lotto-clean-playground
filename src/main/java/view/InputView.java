package view;

import java.util.Scanner;

public class InputView {
    private static Scanner input = new Scanner(System.in);

    public static int inputMoney(){
        System.out.println("구입금액을 입력해 주세요.");
        return input.nextInt();
    }
}
