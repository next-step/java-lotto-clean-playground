package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    public Lotto generateLotto() {
        List<Integer> numbers = generateNumbers();
        List<Integer> lottoNumbers = generateLottoNumbers(numbers);
        return new Lotto(convertNumbersToLottoNumbers(lottoNumbers));
    }

    private List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    private List<Integer> generateLottoNumbers(List<Integer> numbers) {
        Collections.shuffle(numbers);
        return selectLottoNumbers(numbers);
    }

    private List<Integer> selectLottoNumbers(List<Integer> numbers) {
        List<Integer> lottoNumbers = new ArrayList<>();
        for(int i = 0; i < Lotto.LOTTO_SIZE; i++) {
            lottoNumbers.add(numbers.get(i));
        }
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private List<LottoNumber> convertNumbersToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }
}
