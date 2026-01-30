package domain;

import java.util.List;

public class LottoTicket {
    private final LottoNumbers numbers;

    public LottoTicket(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public List<Integer> numbers() {
        return numbers.asList();
    }

    public LottoNumbers lottoNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.asList().toString();
    }
}
