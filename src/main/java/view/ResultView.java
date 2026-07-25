package view;

import domain.LottoCountByMatchNumber;
import domain.PrizeMoneyTable;
import domain.PurchasedLottoNumbers;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ResultView {
    private static final int MINIMUM_PRIZE_MATCH_COUNT = 3;

    public static void printLottoResult(PurchasedLottoNumbers purchasedLottoNumbers) {
        System.out.println(purchasedLottoNumbers.size() + "개를 구매했습니다.");
        for (List<Integer> purchasedLottoNumber : purchasedLottoNumbers.getPurchasedLottoNumbers()) {
            System.out.println(purchasedLottoNumber);
        }
    }

    public static void printWinningStatistics(LottoCountByMatchNumber lottoCountByMatchNumber, PrizeMoneyTable prizeMoneyTable, Integer purchaseAmount){
        System.out.println("\n당첨 통계");
        System.out.println("----------");
        SortedSet<Integer> matchNumbers = new TreeSet<>(prizeMoneyTable.matchNumbers()).tailSet(MINIMUM_PRIZE_MATCH_COUNT);
        for (int matchNumber : matchNumbers) {
            System.out.println(matchNumber + "개 일치 (" + prizeMoneyTable.getPrize(matchNumber) + "원) : " + lottoCountByMatchNumber.get(matchNumber) + "개");
        }
    }

    public static void printRateOfReturn(Double rateOfReturn){
        System.out.printf("\n총 수익률은  %.2f 입니다. %s", rateOfReturn, result(rateOfReturn));
    }

    private static String result(Double rateOfReturn){
        if(rateOfReturn > 1){
            return "(기준이 1이기 때문에 이득을 보셨습니다.)";
        }
        return "(기준이 1이기 때문에 결과적으로 손해입니다)";
    }
}
