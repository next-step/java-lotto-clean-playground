import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printLottos(List<Lotto> lotto, int manualAmount, int autoLottoCount){
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualAmount, autoLottoCount);
        lotto.forEach(System.out::println);
    }

    public void printWinningStatistics(Map<Rank, Long> winningLottos) {
        System.out.println("당첨 통계\n---------");
        sortWinningLottos(winningLottos).forEach(this::printRankInfo);
    }

    private EnumMap<Rank, Long> sortWinningLottos(Map<Rank, Long> winningLottos) {
        EnumMap<Rank, Long> sortedMap = new EnumMap<>(Rank.class);
        winningLottos.entrySet().stream()
                .filter(entry -> entry.getKey() != Rank.UNRANK)
                .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
    }

    private void printRankInfo(Rank rank, Long count) {
        System.out.printf("%d개 일치%s (%d원) - %d개\n",
                rank.getMatch(),
                rank.hasBonus() ? " + 보너스 볼" : "",
                rank.getReward(),
                count
        );
    }

    public void printProfitRate(double profitRate){
        System.out.printf("총 수익률은 %.2f 입니다.\n", profitRate);
    }

    public void printInputManualLottoMessage(){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
