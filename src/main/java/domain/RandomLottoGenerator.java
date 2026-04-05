package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator {

    private final List<Integer> seedNumbers;

    public RandomLottoGenerator() {
        this.seedNumbers = initializeNumbers();
    }

    private List<Integer> initializeNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public Lotto generate() {
        Collections.shuffle(seedNumbers);
        List<Integer> LottoNumbers = new ArrayList<>(seedNumbers.subList(0, Lotto.LOTTO_NUMBER_COUNT));
        Collections.sort(LottoNumbers);

        return parseLottoNumber(LottoNumbers);
    }

    public Lotto parseLottoNumber(List<Integer> LottoNumbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : LottoNumbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return new Lotto(lottoNumbers);
    }
}
