package view;

import domain.lotto.LottoResult;
import domain.lotto.LottoResult.Prize;
import domain.lotto.LottoTicket;
import domain.lotto.Money;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    
    public static void printResult(List<LottoTicket> lottoTickets) {
        System.out.println();
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public static void printStatistics(Money myMoney, LottoResult lottoResult, List<LottoTicket> lottoTickets) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        Map<Prize, Integer> prizes = new LinkedHashMap<>();
        prizes.put(Prize.FOURTH, 0);
        prizes.put(Prize.THIRD, 0);
        prizes.put(Prize.SECOND, 0);
        prizes.put(Prize.FIRST, 0);
        
        lottoTickets.forEach(lottoTicket -> {
            Prize prize = lottoResult.findPrize(lottoTicket);
            prizes.put(prize, prizes.getOrDefault(prize, 0) + 1);
        });

        Money total = Money.ZERO;
        for (Map.Entry<Prize, Integer> prizeIntegerEntry : prizes.entrySet()) {

            Prize prize = prizeIntegerEntry.getKey();
            Integer match = prize.getMatch();
            Money money = prize.getMoney();
            if (prize == Prize.LOSING_TICKET) continue;

            Integer count = prizeIntegerEntry.getValue();
            System.out.println(match + "개 일치 (" + money.getAmount() + "원) - " + count + "개");
            total = total.plus(money.multiply(count));
        }

        double profitRate = (double) total.getAmount() / myMoney.getAmount();
        profitRate = Math.floor(profitRate * 100) / 100;
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
    }
}
