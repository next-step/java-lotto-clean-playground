package lotto.view;

import java.util.Scanner;

public class InputView {

    public String getMoney() {
        Scanner scanner = new Scanner(System.in);
        String money = scanner.next();
        scanner.close();
        return money;
    }
}
