package view;

import config.ResultType;
import java.util.Map;
import java.util.Map.Entry;
import model.Lotto;

import java.util.List;
import model.LottoStat;

public class ResultPrinter {

    private static String RESULT_START = "당첨 통계";
    private static String RESULT_LINE = "---------";
    private static String RATE_ONE_UNDER = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    public static void printLottos(final List<Lotto> lottos, final int manualLottoCount) {
        if (manualLottoCount == 0) {
            printAutoLottos(lottos);
            return;
        }
        printManualLottos(lottos, manualLottoCount);
    }

    private static void printAutoLottos(final List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        printCreatedLottos(lottos);
    }

    private static void printManualLottos(final List<Lotto> lottos, final int manualLottoCount) {
        System.out.print("\n" + "수동으로 " + manualLottoCount + "장, ");
        final int autoLottoCount = lottos.size() - manualLottoCount;
        System.out.println("자동으로 " + autoLottoCount + "개를 구매했습니다.");
        printCreatedLottos(lottos);
    }

    private static void printCreatedLottos(final List<Lotto> lottos) {
        lottos.forEach(System.out::println);
    }

    public static void printLottoResult(final LottoStat stat) {
        System.out.println("\n" + RESULT_START);
        System.out.println(RESULT_LINE);
        printResultType(stat);
    }

    private static void printResultType(final LottoStat stat) {
        Map<ResultType, Integer> statics = stat.getStatics();
        for (Entry<ResultType, Integer> entry : statics.entrySet()) {
            printOneResultType(entry.getKey(), entry.getValue());
        }
    }

    private static void printOneResultType(final ResultType type, final Integer count) {
        System.out.print(type.getDescription());
        System.out.print(" (" + type.getWinningPrice() + "원)");
        System.out.print("- " + count + "개\n");
    }

    public static void printTotalReturnRate(final double rate) {
        System.out.printf("총 수익률은 " + "%.2f" + "입니다.", rate);
        if (rate < 1) {
            System.out.println(RATE_ONE_UNDER);
        }
    }

}
