package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LottoNumberGenerator {
    public LottoNumberGenerator() {
    }

    private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
    public LottoNumbers generateLottoNumbers() {
        threadLocalRandom.nextInt(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER);

        List<LottoNumber> value = new ArrayList<>();
        for (int i = 0; i < LottoNumbers.SIZE; i++) {
            value.add(generateLottoNumber());
        }

        return new LottoNumbers(value);
    }

    private LottoNumber generateLottoNumber() {
        return new LottoNumber(threadLocalRandom.nextInt(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER));
    }
}
