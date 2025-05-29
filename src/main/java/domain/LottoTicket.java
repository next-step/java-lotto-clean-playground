package domain;

import java.util.List;

public class LottoTicket {
    private final LottoNumbers numbers;

    public LottoTicket(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public Rank countMatch(WinningNumbers winningNumbers) {
        return numbers.countMatch(winningNumbers.getWinningNumbers());
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }
}
