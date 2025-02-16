import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {

    public Map<Rank, Long> calcWinningLottos(List<Lotto> lottos, List<Integer> winningNumbers, int bonusBall) {
        Map<Rank, Long> winningLottos = new HashMap<>();

        for (Rank rank : Rank.values()) {
            winningLottos.put(rank, 0L);
        }

        Map<Rank, Long> result = lottos.stream()
                .map(lotto -> {
                    int matchCount = (int) lotto.getNumbers().stream()
                            .filter(winningNumbers::contains)
                            .count();
                    boolean hasBonus = lotto.getNumbers().contains(bonusBall);
                    return Rank.getRank(matchCount, hasBonus);
                })
                .filter(rank -> rank != Rank.UNRANK)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        winningLottos.putAll(result);

        return winningLottos;
    }

    public double calcProfitRate(Map<Rank, Long> winningLottos, int lottoAmount) {
        long totalEarnings = winningLottos.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();

        return ((double) totalEarnings / lottoAmount);
    }

}
