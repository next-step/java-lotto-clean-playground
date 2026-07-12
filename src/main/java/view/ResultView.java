package view;

import domain.LottoPrice;
import domain.MatchCount;

public class ResultView {

    public void printTicketNumbers(int ticketNumber) {
        System.out.println();
        System.out.println(ticketNumber + "개를 구매했습니다.");
    }


    public void printManualAuto(int manualCount, int autoCount) {
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
    }

    public void printLottoMatch(MatchCount matchCount) {
        int matchFifth = matchCount.getCount(LottoPrice.MATCH_3);
        int matchFourth = matchCount.getCount(LottoPrice.MATCH_4);
        int matcThird = matchCount.getCount(LottoPrice.MATCH_5);
        int matchSecond = matchCount.getCount(LottoPrice.MATCH_5_BONUS);
        int matchFirst = matchCount.getCount(LottoPrice.MATCH_6);


        System.out.println("3개 일치 (5000원) - " +  matchFifth + "개");
        System.out.println("4개 일치 (50000원) - " + matchFourth + "개");
        System.out.println("5개 일치 (1500000원) - " + matcThird + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30000000) - " + matchSecond + "개");
        System.out.println("6개 일치 (2000000000원) - " + matchFirst + "개");
    }

    public void printLottoProfit(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.%n", profitRate);
    }
}
