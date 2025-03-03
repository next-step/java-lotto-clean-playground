package model;

import java.util.*;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto() {
        this.lottoNumbers = new LottoNumbers();
    }

    public Lotto(List<Integer> numbers) {
        this.lottoNumbers = new LottoNumbers(numbers);
    }

    public List<Integer> getSortedNumbers() {
        return lottoNumbers.getSortedNumbers();
    }
}
