package domain.lotto;

import domain.number.LottoNumberCombination;
import domain.number.LottoNumber;
import java.util.List;

public class LottoTicket {
    private final LottoNumberCombination numbers;

    public LottoTicket(List<Integer> numbers) {
        this(LottoNumberCombination.from(numbers));
    }

    public LottoTicket(LottoNumberCombination numbers) {
        this.numbers = numbers;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public List<Integer> values() {
        return numbers.values();
    }
}
