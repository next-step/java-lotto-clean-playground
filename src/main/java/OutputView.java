import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printLottos(List<Lotto> lotto, int manualAmount, int autoLottoCount){
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualAmount, autoLottoCount);
        lotto.forEach(System.out::println);
    }

    public void printWinningStatistics(Map<Rank, Long> winningLottos) {
        System.out.println("당첨 통계\n---------");
        winningLottos.entrySet().stream()
                .filter(entry -> entry.getKey() != Rank.UNRANK)
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Rank::ordinal).reversed()))
                .forEach(entry -> {
                    Rank rank = entry.getKey();
                    System.out.printf("%d개 일치%s (%d원) - %d개\n",
                            rank.getMatch(),
                            rank.hasBonus() ? " + 보너스 볼" : "",
                            rank.getReward(),
                            entry.getValue()
                    );
                });
    }

    public void printProfitRate(double profitRate){
        System.out.printf("총 수익률은 %.2f 입니다.\n", profitRate);
    }

    public void printInputManualLottoMessage(){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
