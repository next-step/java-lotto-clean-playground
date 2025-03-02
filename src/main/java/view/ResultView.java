package view;

import model.LottoRank;

import java.util.List;

public class ResultView {

    public void displayPurchasedLottoTickets(List<String> tickets, int manualCount) {
        int automaticCount = tickets.size() - manualCount;
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + automaticCount + "개를 구매했습니다.");
        for (String ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void printLottoStatistics(List<String> lottoRankStrings) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (String rankString : lottoRankStrings) {
            System.out.println(rankString);
        }
    }

    public void printEarningsRate(String earningsRate) {
        System.out.println(earningsRate);
    }
}
