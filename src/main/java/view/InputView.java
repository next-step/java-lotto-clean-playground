package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    private static int price;

    public void inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        price = sc.nextInt();
    }

    public int getPrice() {
        return price;
    }
}
