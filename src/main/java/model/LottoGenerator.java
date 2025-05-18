package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<Lotto> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateSinglelotto())
                .collect(Collectors.toList());
    }

    private Lotto generateSinglelotto() {
        List<Integer> shuffled = createShuffledLottoNumbers();
        List<LottoNumber> lottoNumbers = shuffled.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    private List<Integer> createShuffledLottoNumbers() {
        List<Integer> all = IntStream.rangeClosed(LOTTO_MIN, LOTTO_MAX).boxed()
                .collect(Collectors.toList());
        Collections.shuffle(all);
        return all;
    }


    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

}
