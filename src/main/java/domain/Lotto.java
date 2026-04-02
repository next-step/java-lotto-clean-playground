package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_UPPER_BOUND = 45;

    private final List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Lotto() {
        this.numbers = getSingleLotto();
    }

    private ArrayList<Integer> getSingleLotto() {
        ArrayList<Integer> lottoNumbers = generateLottoNumbersArray();
        Collections.shuffle(lottoNumbers);
        List<Integer> subNumbers = lottoNumbers.subList(0, 6);
        Collections.sort(subNumbers);
        return new ArrayList<>(subNumbers);
    }

    private ArrayList<Integer> generateLottoNumbersArray() {
        ArrayList<Integer> lottoNumbers = new ArrayList<>(LOTTO_UPPER_BOUND);
        for(int i = 0 ; i < LOTTO_UPPER_BOUND ; i++) {
            lottoNumbers.add(i, i + 1);
        }
        return lottoNumbers;
    }
}
