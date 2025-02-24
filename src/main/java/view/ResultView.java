package view;

import model.Lotto;

import java.util.List;

public class ResultView {

    public void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
        System.out.println();
    }

    public void printLottery(List<Lotto> lottery) {
        for (Lotto lotto : lottery) {
            System.out.println(lotto);
        }
    }
}
