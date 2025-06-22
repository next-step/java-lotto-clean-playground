package lotto.ui;

import java.util.Map.Entry;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Payment;
import lotto.domain.Rank;
import lotto.domain.WinningResult;

import java.util.Map;

public class OutputView {

  public static void printLottos(final Lottos lottos) {
    System.out.println("\n" + lottos.size() + "개를 구매했습니다.");

    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Lotto lotto : lottos) {
      sb.append(lotto).append(", ");
    }
    sb.append("]");

    System.out.println(sb.toString());
  }

  public static void printResults(final WinningResult winningResult) {
    System.out.println("\n당첨 통계\n---------");

    Map<Rank, Integer> rankStats = winningResult.getWinningResult();

    for (Entry<Rank, Integer> entry : rankStats.entrySet()) {
      final Rank rank = entry.getKey();
      final int count = entry.getValue();

      if (rank == Rank.NONE) {
        continue;
      }

      if (rank.isBonusMatch()) {
        System.out.printf("5개 일치, 보너스 볼 일치 (%,d원)- %d개\n", rank.getPrizeMoney(), count);
        continue;
      }

      System.out.printf("%d개 일치 (%,d원)- %d개\n", rank.getMatchCount(), rank.getPrizeMoney(), count);
    }
  }

  public static void printPrize(final WinningResult winningResult, final Payment payment) {
    System.out.printf("총 수익율은 %.2f 입니다.", winningResult.calculateReturnRate(payment.value()));
  }
}
