package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator{

    private final List<Integer> lottoNumbersRange;

    public RandomLottoNumberGenerator() {
        this.lottoNumbersRange = new ArrayList<>();
        for (int i = 1; i < 46; i++) {
            this.lottoNumbersRange.add(i);
        }
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(lottoNumbersRange);
        List<Integer> lottoNumber = new ArrayList<>(lottoNumbersRange.subList(0, 6));
        Collections.sort(lottoNumber);
        return lottoNumber;
    }
}
