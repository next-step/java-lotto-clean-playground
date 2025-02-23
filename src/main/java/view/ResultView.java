package view;

import model.Lotto;

import java.util.List;

public class ResultView {

    public void printLottoResult(List<String> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (String ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
