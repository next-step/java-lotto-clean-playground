package view;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public int inputCost() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }
}
