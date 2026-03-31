package view;

import java.util.Scanner;

public class InputView {
    public int inputPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입 금액을 입력해 주세요.");
        return scanner.nextInt();
    }
}
