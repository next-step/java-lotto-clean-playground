package generator;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final List<LottoNumber> LOTTO_NUMBERS = createLottoNumbers();

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LOTTO_NUMBERS);

        Collections.shuffle(lottoNumbers);

        return new ArrayList<>(lottoNumbers.subList(0, LOTTO_SIZE));
    }

    private static List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_END_NUMBER)
                .mapToObj(LottoNumber::new)
                .toList();
    }
}
