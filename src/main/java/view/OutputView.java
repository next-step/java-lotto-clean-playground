package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Match;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputView {

    public OutputView() {
    }

    public void printPurchasedCount(int autoCount, int manualCount) {
        if (manualCount > 0 && autoCount > 0) {
            System.out.print("수동으로 " + manualCount + "장, " + "자동으로 " + autoCount + "개를");
        }

        else if (manualCount > 0) {
            System.out.print("수동으로 " + manualCount + "장을");
        }

        else if (autoCount > 0) {
            System.out.print("자동으로 " + autoCount + "개를");
        }

        System.out.println(" 구매했습니다.");
    }

    public void printLottosNumbers(List<Lotto> lottos) {
        List<List<LottoNumber>> flatLottosNumbers = lottos.stream()
                .map(Lotto::getLottoNumbers)
                .toList();

        for (List<LottoNumber> lottosNumber : flatLottosNumbers) {
            System.out.println(lottosNumber);
        }
    }

    public void printStatistic(Map<Match, Integer> statistic) {
        System.out.println("당첨 통계\n" +
                "---------");

        Arrays.stream(Match.values())
                .filter(m -> m != Match.NONE)
                .forEach(m -> {
                    String printFormat = m.getCount() + "개 일치 (" + m.getPrice() + "원) - ";
                    System.out.println(printFormat + statistic.get(m));
                });

//        for (Match m: Match.values()) {
//            String printFormat = m.getCount() + "개 일치 (" + m.getPrice() + "원) - ";
//            System.out.println(printFormat + statistic.get(m));
//        }
    }

    public void printEarnRate(double rate) {
        System.out.print("총 수익률은 " + Math.floor(rate * 100)/100.0 + "입니다.");

        if (rate < 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
            return;
        }

        System.out.println("(기준이 1이기 때문에 결과적으로 이득라는 의미임)");
    }
}
