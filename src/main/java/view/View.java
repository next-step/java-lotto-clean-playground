package view;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public int inputCost() {
        System.out.println("구입금액을 입력해 주세요.");
        int cost=scanner.nextInt();
        System.out.printf("입력하신 금액은 %d원 입니다.\n", cost);
        return cost;
    }
}
