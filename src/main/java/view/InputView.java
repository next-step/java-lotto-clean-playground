package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    public int inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        int price = sc.nextInt();

        return price;
    }
}
