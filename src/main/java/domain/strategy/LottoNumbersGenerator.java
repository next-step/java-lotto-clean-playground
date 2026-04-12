package domain.strategy;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator implements NumbersGenerator<LottoNumber> {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private static final List<LottoNumber> allLottoNumbers = LottoNumber.CACHE;

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(allLottoNumbers);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .toList();
    }
}
