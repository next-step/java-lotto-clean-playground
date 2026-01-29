package domain.lotto;

import static domain.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static domain.lotto.LottoNumber.MIN_LOTTO_NUMBER;
import static domain.lotto.LottoNumbers.SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    private final List<Integer> numbers;

    public LottoNumberGenerator() {
        this.numbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
    }

    public LottoNumbers generate() {
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = numbers.stream()
                .limit(SIZE)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }
}
