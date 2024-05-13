package view;

import java.util.Scanner;

public class InputView {
    private Scanner scanner=new Scanner(System.in);

    public int getMoney() {
        System.out.println("금액을 입력하세요:");
        return scanner.nextInt();
    }
}
