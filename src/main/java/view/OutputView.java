package view;

import domain.LottoResult;
import domain.LottoTicket;
import domain.Rank;

import java.util.List;

public class OutputView {
    public void printPurchased(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printTickets(List<LottoTicket> tickets) {
        tickets.forEach(System.out::println);
    }
    public void printResult(LottoResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5000원) - " + result.countOf(Rank.THREE) + "개");
        System.out.println("4개 일치 (50000원) - " + result.countOf(Rank.FOUR) + "개");
        System.out.println("5개 일치 (1500000원) - " + result.countOf(Rank.FIVE) + "개");
        System.out.println("6개 일치 (2000000000원) - " + result.countOf(Rank.SIX) + "개");
        System.out.println("총 수익률은 " + String.format("%.2f", result.profitRate()) + "입니다. ");
    }
}
