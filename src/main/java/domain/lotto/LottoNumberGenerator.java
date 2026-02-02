package domain.lotto;

import static domain.lotto.LottoNumbers.SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    private final List<Integer> numbers;

    public LottoNumberGenerator(int minLottoNumber, int maxLottoNumber) {
        this.numbers = new ArrayList<>();
        for (int i = minLottoNumber; i <= maxLottoNumber; i++) {
            numbers.add(i);
        }
    }

    public LottoNumbers generate() {
        List<Integer> shuffled = new ArrayList<>(numbers);
        Collections.shuffle(shuffled);

        List<LottoNumber> lottoNumbers = shuffled.stream()
                .limit(SIZE)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoNumbers(lottoNumbers);
    }
}
