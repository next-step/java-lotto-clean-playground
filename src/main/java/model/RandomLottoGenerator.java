package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    public static List<Lotto> generateLottoNumbers(Integer count) {
        return IntStream.range(0, count)
            .mapToObj(i -> generateLottoNumber())
            .toList();
    }

    private static Lotto generateLottoNumber() {
        Set<LottoNumber> lottoNumbers = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 46))
            .distinct()
            .limit(6)
            .mapToObj(it -> new LottoNumber(it))
            .collect(Collectors.toSet());
        return new Lotto(new ArrayList<>(lottoNumbers));
    }
}
