package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {
    private static final List<Integer> NUMBERS = new ArrayList<>();
    static {
        for (int i = 1; i <= 45; i++) {
            NUMBERS.add(i);
        }
    }

    private final List<Integer> lottoNumbers;

    public LottoNumber() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(numbers);
        this.lottoNumbers = new ArrayList<>(numbers.subList(0, 6));
        Collections.sort(this.lottoNumbers);
    }

    public LottoNumber(List<Integer> manualNumbers) {
        this.lottoNumbers = new ArrayList<>(manualNumbers);
        Collections.sort(this.lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
