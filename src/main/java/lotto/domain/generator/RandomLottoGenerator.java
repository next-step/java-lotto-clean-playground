package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoNumberGenerator {
    private static final List<Integer> LOTTO_NUMBER_POOL = IntStream.rangeClosed(1, 45)
            .boxed()
            .toList();

    @Override
    public List<Integer> generateLottoNumbers() {
        List<Integer> shuffledNumbers = new ArrayList<>(LOTTO_NUMBER_POOL); // 복사본을 만들어서 셔플
        Collections.shuffle(shuffledNumbers);
        return shuffledNumbers.subList(0, 6);
    }
}
