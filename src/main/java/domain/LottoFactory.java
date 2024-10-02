package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    //수동 로또 생성 메서드
    public static List<Lotto> createManualLottos(List<List<Integer>> manualNumbers) {
        return manualNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    //자동 로또 생성 메서드
    public static Lottos createAutoLottos(int numberOfAutoLottos) {
        List<Lotto> lottoList = generateLottos(numberOfAutoLottos);
        return new Lottos(lottoList);
    }
    //구입한 자동 로또개수만큼 로또 자동 생성
    private static List<Lotto> generateLottos(int numberOfAutoLottos) {

        return IntStream.range(0, numberOfAutoLottos)
                .mapToObj(i -> new Lotto())
                .collect(Collectors.toList());
    }
}
