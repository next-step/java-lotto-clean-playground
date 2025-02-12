import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {

    public void calcWiningLottos(List<Lotto> lottos, List<Integer> winingNums){
        Map<Integer, Long> winingLottos = lottos.stream()
                .collect(Collectors.groupingBy(
                        lotto -> (int) lotto.getNumbers().stream().filter(winingNums::contains).count(),
                        Collectors.counting()
                )); //key : 맞은 개수, 로또 개수
    }
}
