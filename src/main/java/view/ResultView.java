package view;

import domain.Lotto;
import domain.LottoRank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    public void printPurchasedLottos(List<Lotto> lottos, int manualLottoCount, int autoLottoCount) {
        System.out.println("수동으로 %d장, 자동으로 %d개를 구매했습니다."
                .formatted(manualLottoCount, autoLottoCount));

        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    public void printLottoStatistics(Map<LottoRank, Integer> rankCounts, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (LottoRank rank : LottoRank.values()) {
            System.out.println("%s(%d원)- %d개"
                    .formatted(rank.getDescription(), rank.getPrize(), rankCounts.get(rank)));
        }

        System.out.println("총 수익률은 %.2f입니다.".formatted(profitRate));
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers()
                .stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]")));
    }
}
