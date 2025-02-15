import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OutputView {

    public void printLottos(List<Lotto> lotto, int manualAmount, int autoLottoCount){
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualAmount, autoLottoCount);
        lotto.forEach(System.out::println);
    }

    public void printWinningStatistics(Map<Integer, Long> winingLottos){
        System.out.println("당첨통계\n---------");
        winingLottos.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.printf("%d개 일치 (%d)- %d개\n", entry.getKey(), Rank.getReward(entry.getKey()), entry.getValue())
                );
    }

    public void printProfitRate(double profitRate){
        System.out.printf("총 수익률은 %.2f 입니다\n.", profitRate);
    }

    public void printInputManualLottoMessage(){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
