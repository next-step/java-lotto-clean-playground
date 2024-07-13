package view;

import domain.lotto.LottoTicket;
import domain.lotto.dto.StatistsDto;

import java.util.List;

public class OutputView {

    public static void printResult(List<LottoTicket> lottoTickets) {
        System.out.println();
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
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
                final String bonusComment = m.hasHasBonusNumber() ? ", 보너스 볼 일치" : "";
                System.out.println(m.getMatch() + "개 일치" + bonusComment + "(" + m.getAmount() + "원) - " + m.getCount() + "개");
            });
        
        System.out.printf("총 수익률은 %.2f입니다.", statistsDto.getProfitRate());
    }
}
