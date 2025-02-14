import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {

    public Map<Integer, Long> calcWiningLottos(List<Lotto> lottos, List<Integer> winingNums){
        //key : 맞은 개수, 로또 개수
        return lottos.stream()
                .collect(Collectors.groupingBy(
                        lotto -> (int) lotto.getNumbers().stream().filter(winingNums::contains).count(),
                        Collectors.counting()
                ));
    }
}
