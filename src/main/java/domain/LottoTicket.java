package domain;

import java.util.List;

public class LottoTicket {
    private final LottoNumbers numbers;

    public LottoTicket(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public int countMatch(LottoNumbers winningNumbers) {
        return numbers.countMatch(winningNumbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
