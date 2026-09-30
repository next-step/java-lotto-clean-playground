package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> numbers = LottoNumber.allNumbers();

        Collections.shuffle(numbers);

        List<LottoNumber> lottoNumbers = new ArrayList<>(numbers.subList(0, Lotto.requiredNumberCount()));

        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
