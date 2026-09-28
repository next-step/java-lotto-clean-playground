package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        this.lottoNumbers = numbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public int countMatch(WinningNumbers winningNumbers) {
        int matchCount = 0;
        for (int number : lottoNumbers) {
            matchCount += matchScore(number, winningNumbers);
        }
        return matchCount;
    }

    private int matchScore(int number, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }

}
