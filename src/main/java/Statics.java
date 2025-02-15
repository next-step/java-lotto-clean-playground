import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {

    public Map<Rank, Long> calcWinningLottos(List<Lotto> lottos, List<Integer> winingNumbers, int bonusBall) {
        Map<Rank, Long> winingLottos = new HashMap<>();

        // 초기화: 모든 Rank 값에 대해 0으로 설정
        for (Rank rank : Rank.values()) {
            winingLottos.put(rank, 0L);
        }

        Map<Rank, Long> result = lottos.stream()
                .map(lotto -> {
                    int matchCount = (int) lotto.getNumbers().stream()
                            .filter(winingNumbers::contains)
                            .count();
                    boolean hasBonus = lotto.getNumbers().contains(bonusBall); // 보너스 볼 포함 여부
                    return Rank.getRank(matchCount, hasBonus);
                })
                .filter(rank -> rank != Rank.UNRANK) // 꽝 제외
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        winingLottos.putAll(result);

        return winingLottos;
    }

    public double calcProfitRate(Map<Rank, Long> winingLottos, int lottoAmount) {
        long totalEarnings = winingLottos.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();

        return ((double) totalEarnings / lottoAmount);
    }

}
