package view;

import model.Lotto;

import java.util.List;
import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public int inputCost() {
        System.out.println("구입금액을 입력해 주세요.");
        int cost=scanner.nextInt();
        int ticketAmount=cost/1000;
        System.out.printf("%d개를 구매했습니다.\n", ticketAmount);
        return ticketAmount;
    }

    public void printLottos(List<Lotto> lottos){
        for(Lotto lotto : lottos){
            printLotto(lotto);
        }
    }

    public void printLotto(Lotto lotto){
            System.out.println(lotto);
    }
}
