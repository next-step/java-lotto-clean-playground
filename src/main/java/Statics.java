import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {

    public Map<Integer, Long> calcWiningLottos(List<Lotto> lottos, List<Integer> winingNumbers){
        //key : 맞은 개수, 로또 개수
        return lottos.stream()
                .collect(Collectors.groupingBy(
                        lotto -> (int) lotto.getNumbers().stream().filter(winingNumbers::contains).count(),
                        Collectors.counting()
                ));
    }

    public double calcProfitRate(Map<Integer, Long> winingLottos, int lottoAmount) {
        long totalEarnings = winingLottos.entrySet().stream()
                .mapToLong(entry -> Rank.getReward(entry.getKey()) * entry.getValue())
                .sum();

        return ((double) totalEarnings / lottoAmount) * 100;
    }
}
