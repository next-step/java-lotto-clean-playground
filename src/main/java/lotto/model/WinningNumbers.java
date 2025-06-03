package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final LottoNumbers numbers;
    private final BonusBall bonusBall;

    public WinningNumbers(LottoNumbers numbers, BonusBall bonusBall) {
        this.numbers = numbers;
        this.bonusBall = bonusBall;
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    public int getBonusNumber() {
        return bonusBall.getValue();
    }
}
