package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final LottoNumbers numbers;
    private final int bonusBall;

    public WinningNumbers(List<Integer> numbers, int bonusBall) {
        this.numbers = new LottoNumbers(numbers);
        this.bonusBall = bonusBall;
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
