package view;

import domain.MatchCount;

public class ResultView {

    public void printTicketNumbers(int ticketNumber) {
        System.out.println();
        System.out.println(ticketNumber+"개를 구매했습니다.");
    }

    public void printLottoNumbers(String lottoNumbers) {
        System.out.println("로또 번호: " + lottoNumbers);
    }

    public void printLottoMatch(MatchCount matchCount) {
        int match3Count = matchCount.getMatch3Count();
        int match4Count = matchCount.getMatch4Count();
        int match5Count = matchCount.getMatch5Count();
        int match6Count = matchCount.getMatch6Count();

        System.out.println("3개 일치 (5000원) - " + match3Count + "개");
        System.out.println("4개 일치 (50000원) - " + match4Count + "개");
        System.out.println("5개 일치 (1500000원) - " + match5Count + "개");
        System.out.println("6개 일치 (2000000000원) - " + match6Count + "개");
    }

    public void printLottoProfit(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.%n",profitRate);
    }
}
