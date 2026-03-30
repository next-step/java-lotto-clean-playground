package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumbersGenerator implements NumbersGenerator {
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private static final List<Integer> allLottoNumbers =
            IntStream.rangeClosed(LOTTO_MIN_RANGE, LOTTO_MAX_RANGE).boxed().toList();

    @Override
    public List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>(allLottoNumbers);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .toList();
    }
}
