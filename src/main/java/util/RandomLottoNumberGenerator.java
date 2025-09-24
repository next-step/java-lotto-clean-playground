package util;

import model.LottoNumber;
import model.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public LottoNumbers generate() {
        List<LottoNumber> numbers = createLottoNumbers();
        Collections.shuffle(numbers);
        return new LottoNumbers(numbers.subList(0, 6));
    }

    public List<LottoNumber> createLottoNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = 1; i < 46; i++) {
            numbers.add(new LottoNumber(i));
        }
        return numbers;
    }
}
