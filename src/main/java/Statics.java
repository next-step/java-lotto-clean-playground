import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Objects;

public class Statics {

    public Map<Rank, Long> calcWinningLottos(List<Lotto> lottos, List<Integer> winningNumbers, int bonusBall) {
        Map<Rank, Long> winningLottos = initWinningLottos();
        Map<Rank, Long> result = countWinningRanks(lottos, winningNumbers, bonusBall);
        winningLottos.putAll(result);
        return winningLottos;
    }

    private Map<Rank, Long> initWinningLottos() {
        Map<Rank, Long> map = new HashMap<>();
        for (Rank rank : Rank.values()) map.put(rank, 0L);
        return map;
    }

    private Map<Rank, Long> countWinningRanks(List<Lotto> lottos, List<Integer> winningNumbers, int bonusBall) {
        return lottos.stream().filter(Objects::nonNull)
                .map(lotto -> Rank.getRank(countMatchingNumbers(lotto, winningNumbers), lotto.getNumbers().contains(bonusBall)))
                .filter(rank -> rank != Rank.UNRANK)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    private int countMatchingNumbers(Lotto lotto, List<Integer> winningNumbers) {
        return (int) lotto.getNumbers().stream().filter(winningNumbers::contains).count();
    }

    public double calcProfitRate(Map<Rank, Long> winningLottos, int lottoAmount) {
        long totalEarnings = winningLottos.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();

        return ((double) totalEarnings / lottoAmount);
    }

}
