package domain;

import domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    public static List<Lotto> generateLottos(int count) {
        return Stream.generate(Lotto::generate)
                .limit(count)
                .collect(Collectors.toList());
    }
}
