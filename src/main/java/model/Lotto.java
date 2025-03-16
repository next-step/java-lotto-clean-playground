package model;

import java.util.*;

//로또 한 장
public class Lotto {
    private final LottoNumbers lottoNumbers;

    //수동 로또
    public Lotto(List<Integer> numbers) {
        this.lottoNumbers = new LottoNumbers(numbers);
    }

    //자동 로또
    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.getNumbers();
    }
}
