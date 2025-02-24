package view;

import domain.Lotto;
import domain.Lottos;
import domain.Rank;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class ResultView
{
    public static void printBuyLottoCount(List<Integer> ticketCount)
    {
        System.out.printf("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", ticketCount.get(0), ticketCount.get(1));
    }

    public static void printLottos(List<Lotto> lottos)
    {
        for (Lotto lotto : lottos)
            System.out.println(lotto.getLottoNumbers());
    }

    public static void calculateProceed(List<Integer> lastweekGoalNumber, int lastweekBonusGoalNumber, Lottos tickets)
    {
        Map<Rank, Long> lottosResult = tickets.getTotalCorrectCount(lastweekGoalNumber, lastweekBonusGoalNumber);

        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (%d원)- %d개\n", Rank.FIFTH.getPrize(), lottosResult.get(Rank.FIFTH));
        System.out.printf("4개 일치 (%d원)- %d개\n", Rank.FOURTH.getPrize(), lottosResult.get(Rank.FOURTH));
        System.out.printf("5개 일치 (%d원)- %d개\n", Rank.THIRD.getPrize(), lottosResult.get(Rank.THIRD));
        System.out.printf("5개 일치 (%d원)- %d개\n", Rank.SECOND.getPrize(), lottosResult.get(Rank.SECOND));
        System.out.printf("6개 일치 (%d원)- %d개\n", Rank.FIRST.getPrize(), lottosResult.get(Rank.FIRST));

        double totalProceed = Stream.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
                .mapToDouble(rank -> lottosResult.get(rank) * rank.getPrize())
                .sum() / (double) tickets.getCost();

        System.out.println("총 수익률은 " + String.format("%.2f", totalProceed) + "입니다.");
    }

}