import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {

    public Map<Integer, Long> calcWiningLottos(List<Lotto> lottos, List<Integer> winingNumbers) {

        Map<Integer, Long> winingLottos = new HashMap<>();
        for (int i = 3; i <= 6; ++i) {
            winingLottos.put(i, 0L);
        }

        Map<Integer, Long> result = lottos.stream()
                .collect(Collectors.groupingBy(
                        lotto -> (int) lotto.getNumbers().stream().filter(winingNumbers::contains).count(),
                        Collectors.counting()
                )).entrySet().stream()
                .filter(entry -> entry.getKey() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        winingLottos.putAll(result);

        return winingLottos;
    }

    public double calcProfitRate(Map<Integer, Long> winingLottos, int lottoAmount) {
        long totalEarnings = winingLottos.entrySet().stream()
                .mapToLong(entry -> Rank.getReward(entry.getKey()) * entry.getValue())
                .sum();

        return ((double) totalEarnings / lottoAmount);
    }
}
