package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoNumber;

public class RandomLottoGenerator implements LottoNumberGenerator {
    private static final List<LottoNumber> LOTTO_NUMBER_POOL = IntStream.rangeClosed(1, 45)
            .mapToObj(LottoNumber::valueOf)
            .toList();

    @Override
    public List<LottoNumber> generateLottoNumbers() {
        List<LottoNumber> shuffledNumbers = new ArrayList<>(LOTTO_NUMBER_POOL);
        Collections.shuffle(shuffledNumbers);
        return shuffledNumbers.subList(0, Lotto.LOTTO_SIZE);
    }
}
