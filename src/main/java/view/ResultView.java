package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Rank;

import java.util.List;

public class ResultView {

    public static void printPurchase(List<Lotto> lottos, int passiveCount, int autoCount) {
        System.out.println("수동으로 " + passiveCount + "장, 자동으로 " + autoCount + "개를 구매했습니다."
        );
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printResult(List<Rank> ranks, double rate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");


        System.out.println("3개 일치 (5,000원) - " + countRank(ranks, Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + countRank(ranks, Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + countRank(ranks, Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + countRank(ranks, Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + countRank(ranks, Rank.FIRST) + "개");
        System.out.printf("총 수익률은 %.2f입니다.\n", rate);
    }

    private static int countRank(List<Rank> ranks, Rank target) {
        int count = 0;

        for (Rank rank : ranks) {
            if (rank == target) {
                count++;
            }
        }

        return count;
    }
}
