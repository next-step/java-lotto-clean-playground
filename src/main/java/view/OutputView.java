package view;

import model.Lotto;
import model.Rank;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printAmount(int autoAmount, int passiveAmount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", passiveAmount, autoAmount);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    public void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void cycle(EnumMap<Rank, Integer> counts) {
        System.out.println("당첨통계");
        System.out.println("---------");
        for (Map.Entry<Rank, Integer> entry : counts.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            int duplCount = rank.getMatchCount();
            int prize = rank.getPrize();

            System.out.printf("%d개 일치 (%d원) - %d개\n", duplCount, prize, count);
        }
    }

    public void printRatio(float ratio) {
        System.out.printf("총 수익률은 %f입니다", ratio);
    }
}
