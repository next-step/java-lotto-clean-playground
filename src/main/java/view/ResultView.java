package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Rank;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ResultView {

    public static void printLottoResult(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos) {
            System.out.println(toNumbers(lotto.getNumbers()));
        }
    }
    private static List<Integer> toNumbers(List<LottoNumber> lotto) {
        List<Integer> numbers = new ArrayList<>();
        for (LottoNumber lottoNumber : lotto) {
            numbers.add(lottoNumber.getLottoNumber());
        }
        return numbers;
    }

    public static void printStats(LottoResult result, int lottoCount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Rank rank : EnumSet.range(Rank.FOURTH, Rank.FIRST)) {
            printRank(result, rank);
        }
        System.out.printf("총 수익률은 %.2f입니다.%n",
                result.calculateReturnRate(lottoCount));
    }

    private static void printRank(LottoResult result, Rank rank) {
        System.out.printf("%d개 일치 (%d원)- %d개%n",
                rank.getMatchCount(), rank.getPrize(), result.getCount(rank));
    }
}
