import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OutputView {

    public void printLottos(List<Lotto> lottos){
        System.out.println("개를 구매했습니다.");
        lottos.forEach(System.out::println);
    }

    public void printWinningStatistics(Map<Integer, Long> winingLottos){
        System.out.println("당첨통계\n---------");
        winingLottos.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 높은 순위부터 출력
                .forEach(entry ->
                        System.out.printf("%d개 일치 (%d)- %d개\n", entry.getKey(), Rank.getReward(entry.getKey()), entry.getValue())
                );
    }

    public void printProfitRate(double profitRate){
        System.out.printf("총 수익률은 %.2f 입니다\n.", profitRate);
    }
}
