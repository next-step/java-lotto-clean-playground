package view;

import config.ResultType;
import java.util.Map;
import java.util.Map.Entry;
import model.Lotto;

import java.util.List;
import model.WinningStat;

public class ResultView {

    private static String RESULT_START = "당첨 통계";
    private static String RESULT_LINE = "---------";


    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(System.out::println);
    }

    public static void printLottoResult(WinningStat stat) {
        System.out.println(RESULT_START);
        System.out.println(RESULT_LINE);
        printResultType(stat);
    }

    private static void printResultType(WinningStat stat) {
        Map<ResultType, Integer> statics = stat.getStatics();
        for (Entry<ResultType, Integer> entry : statics.entrySet()) {
            printOneResultType(entry.getKey(), entry.getValue());
        }
    }

    private static void printOneResultType(ResultType type, Integer count) {
        System.out.print(type.getDescription());
        System.out.print("(" + type.getWinningPrice() + "원)");
        System.out.print("- " + count + "개\n");
    }

}
