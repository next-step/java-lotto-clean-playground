package lotto.view;

import java.util.List;
import java.util.Map;

import lotto.Rank;
import lotto.domain.Lotto;
import lotto.message.ConsoleMessage;

public class OutputView {

    private OutputView() {
    }

    public static void printLottoes(int customCount, List<Lotto> lottoList) {
        System.out.printf("\n" + ConsoleMessage.COUNT_LOTTO.getMessage() + "\n", customCount,
            lottoList.size() - customCount);

        for (Lotto lotto : lottoList) {
            System.out.println(lotto.getLottoNums());
        }
    }

    public static void printStatistics(Map<Rank, Integer> resultMap) {
        System.out.println("\n" + ConsoleMessage.WINNING_STATISTICS.getMessage());
        System.out.println("--------");

        resultMap.forEach(((rank, integer) -> {
            if (rank.equals(Rank._2ND_PLACE)) {
                System.out.printf(ConsoleMessage.CHECK_SAME_2ND.getMessage() + "\n"
                    , rank.getMatch(), rank.getReward(), integer);
            } else {
                System.out.printf(ConsoleMessage.CHECK_SAME.getMessage() + "\n"
                    , rank.getMatch(), rank.getReward(), integer);
            }
        }));
    }

    public static void printReward(String rewardRate) {
        System.out.printf(ConsoleMessage.REWARD_RATE.getMessage(), rewardRate);
    }
}
