package domain.lotto;

import domain.number.LottoNumberCombination;
import domain.number.LottoNumber;
import domain.result.MatchCount;
import java.util.List;

public class LottoTicket {
    private final LottoNumberCombination numbers;

    public LottoTicket(List<Integer> numbers) {
        this(LottoNumberCombination.from(numbers));
    }

    private LottoTicket(LottoNumberCombination numbers) {
        this.numbers = numbers;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public MatchCount countMatching(LottoNumberCombination other) {
        return numbers.countMatching(other);
    }

    public List<Integer> values() {
        return numbers.values();
    }
}
