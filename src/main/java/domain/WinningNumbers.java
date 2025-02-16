package domain;

import java.util.List;

public class WinningNumbers {

    private final LottoNumbers winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = new LottoNumbers(winningNumbers);
    }

    public LottoNumbers getWinningNumbers() {
        return winningNumbers;
    }
}
