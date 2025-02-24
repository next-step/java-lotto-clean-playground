package view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.Lotto;
import model.Rank;

public class OutputView {

    public void printMyLottos(List<Lotto> manual, List<Lotto> auto) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manual.size(), auto.size()));
        manual.stream().forEach(System.out::println);
        auto.stream().forEach(System.out::println);
    }

    public void printRateOfReturn(Integer price, Integer profit) {
        Double rateOfReturn = ((double) profit / price) * 100.0d;
        System.out.println(String.format("총 수익률은 %.2f입니다.", rateOfReturn));
    }

    public void printResult(Map<Rank, Integer> result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Arrays.stream(Rank.values())
            .filter(rank -> rank.matchCount() != 0)
            .forEach(rank -> printResult(rank, result.getOrDefault(rank, 0)));
    }

    private void printResult(Rank rank, Integer count) {
        String bonus = getBonusText(rank);
        System.out.println(String.format("%d개 일치%s(%d원)- %d개",
            rank.matchCount(),
            bonus,
            rank.price(),
            count));
    }

    private String getBonusText(Rank rank) {
        if (rank.containsBonus()) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }
}
