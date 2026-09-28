package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> values) {
        validate(values);

        List<LottoNumber> numbers = new ArrayList<>();
        for (Integer value : values) {
            numbers.add(new LottoNumber(value));
        }

        this.lottoNumbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }

        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 한다.");
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public int countMatch(WinningNumbers winningNumbers) {
        int matchCount = 0;
        for (LottoNumber number : lottoNumbers) {
            matchCount += matchScore(number, winningNumbers);
        }
        return matchCount;
    }

    private int matchScore(LottoNumber number, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }

}
