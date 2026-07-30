package view;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public int inputCost() {
        System.out.println("구입금액을 입력해 주세요.");
        int cost=scanner.nextInt();
        int ticketAmount=cost/1000;
        System.out.printf("%d개를 구매했습니다.", ticketAmount);
        return ticketAmount;
    }
}
