package view;

import static domain.LottoRank.FIRST;
import static domain.LottoRank.FOUR;
import static domain.LottoRank.SECOND;
import static domain.LottoRank.THIRD;

import domain.LottoRank;
import domain.LottoRankTable;
import java.util.List;

public class ResultView {
    public static void printPurchasedLottos(int purchasedLottoCount, List<List<Integer>> lottos){
      System.out.println(purchasedLottoCount + "개를 구입했습니다.");
      for (List<Integer> lotto : lottos) {
        System.out.println(lotto);
      }
    }

    public static void printMatchingNumbers(LottoRankTable rankTable){
      System.out.println("당첨 통계 \n ------------------------");
      List<LottoRank> ranks = List.of(FOUR,THIRD,SECOND,FIRST);
      for (LottoRank rank : ranks) {
        System.out.printf(rank.getMatchingCount() + "개 일치 (" + rank.getPrize() + "원) : " + rankTable.countOf(rank) + "\n");
      }
    }

    public static void printProfitRate(double profitRate){
      System.out.printf("\n총 수익률은 : %.2f입니다. %s", profitRate, lossOrGainMessage(profitRate));
    }

    private static String lossOrGainMessage(double profitRate){
      if(profitRate < 1.0){
        return "( 기준이 1이기 때문에 결과적으로 손해입니다. )";
      }
      return "(기준이 1이기 때문에 결과적으로 이득입니다. )";
    }

}
