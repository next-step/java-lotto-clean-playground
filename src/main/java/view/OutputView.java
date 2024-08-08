package view;

import domain.lotto.LottoTicket;
import domain.lotto.dto.MatchResult;
import domain.lotto.dto.StatistsDto;

import java.util.List;

public class OutputView {

    public static void printResult(int manualCount, int autoCount, List<LottoTicket> lottoTickets) {
        System.out.println();
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public static void printStatistics(StatistsDto statistsDto) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        statistsDto.getMatchResults()
            .forEach(m -> {
                final String bonusComment = getBonusComment(m);
                System.out.println(m.getMatch() + "개 일치" + bonusComment + "(" + m.getAmount() + "원) - " + m.getCount() + "개");
            });
        
        System.out.printf("총 수익률은 %.2f입니다.", statistsDto.getProfitRate());
    }

    private static String getBonusComment(MatchResult m) {
        if (m.hasHasBonusNumber()) {
            return ", 보너스 볼 일치";
        }
        return "";
    }
}
