package view;

import java.util.Scanner;

public class InputView {

    public int inputMoney(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        final int money = scanner.nextInt();

        return money;
    }

}
