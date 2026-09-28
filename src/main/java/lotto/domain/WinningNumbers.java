package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private LottoNumbers numbers;

    public WinningNumbers(List<Integer> values) {
        this.numbers = new LottoNumbers(values);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

}
