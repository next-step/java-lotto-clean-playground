package view;

import java.util.Scanner;

public class InputView {
    public static int inputMoney(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해주세요");
        return scanner.nextInt();
    }
}
