package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.LottoRate;

public class OutputView {

    private OutputView() {
    }

    public static void printLotto(List<List<Integer>> numbers) {
        System.out.printf("%d개를 구매했습니다.%n", numbers.size());
        for (List<Integer> number : numbers) {
            String result = number.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            System.out.printf("[%s]%n", result);
        }
    }

    public static void printStatics(Map<LottoRate, Integer> rate, double rateOfReturn) {
        System.out.println("""
            당첨 통계
            ---------
            """);
        for (LottoRate detail : LottoRate.values()) {
            if (detail == LottoRate.NONE) {
                continue;
            }
            Integer count = rate.getOrDefault(detail, 0);
            System.out.println(detail.getViewMessage(count));
        }
        System.out.printf("총 수익률은 %.2f입니다.%n", rateOfReturn);
    }
}
