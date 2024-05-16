package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("금액을 입력하세요:");
        return scanner.nextInt();
    }

    public String getWinNumber() {
        System.out.println("지난 주 당첨 번호를 입력하시오:");
        return scanner.next();
    }
}
