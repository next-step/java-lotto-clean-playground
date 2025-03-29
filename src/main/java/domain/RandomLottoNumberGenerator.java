package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator{

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<LottoNumber> lottoNumbersRange;

    public RandomLottoNumberGenerator() {
        this.lottoNumbersRange = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            this.lottoNumbersRange.add(new LottoNumber(i));
        }
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(lottoNumbersRange);
        List<LottoNumber> lottoNumber = new ArrayList<>(lottoNumbersRange.subList(0, 6));
        Collections.sort(lottoNumber);
        return lottoNumber;
    }
}
