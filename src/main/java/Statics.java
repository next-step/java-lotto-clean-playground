import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Statics {

    public void calcWiningLottos(List<Lotto> lottos, List<Integer> winingNums){
        Map<Integer, Long> winingLottos = lottos.stream()
                .collect(Collectors.groupingBy(
                        lotto -> (int) lotto.getNumbers().stream().filter(winingNums::contains).count(), // 일치 개수 계산
                        Collectors.counting() // 해당 개수의 로또가 몇 개 있는지 계산
                ));
    }
}
