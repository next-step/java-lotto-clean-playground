package view;

import java.util.Map;

import domain.LottoResult;
import domain.Lottos;
import enumerate.LottoRateEnum;

public class ResultView {

    public static void outputLotto(Lottos lottos, int manualCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualCount, lottos.getCount() - manualCount))
            .append("\n")
            .append(lottos);
        System.out.println(sb);
    }

    public static void outputWinningStatistics(LottoResult lottoResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계").append("\n")
            .append("---------").append("\n");

        Map<LottoRateEnum, Integer> matchCounts = lottoResult.getMatchCounts();
        for (LottoRateEnum lottoRateEnum : LottoRateEnum.values()) {
            int count = matchCounts.getOrDefault(lottoRateEnum, 0);
            sb.append(lottoRateEnum.getFormattedRank(count)).append("\n");
        }

        sb.append(String.format("총 수익률은 %.2f입니다.", lottoResult.getRateOfReturn())).append("\n");
        System.out.println(sb);
    }
}
