package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final List<Integer> ALL_NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed().collect(Collectors.toList());

    public static Lotto generate() {
        Collections.shuffle(ALL_NUMBERS);
        List<LottoNumber> lottoNumbers = ALL_NUMBERS.subList(0, 6).stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}
