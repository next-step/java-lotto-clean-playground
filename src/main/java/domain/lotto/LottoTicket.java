package domain.lotto;

import domain.number.LottoNumberCombination;
import domain.result.LottoResult;
import java.util.List;

public class LottoTicket {
    private final LottoNumberCombination numbers;

    public LottoTicket(List<Integer> numbers) {
        this(LottoNumberCombination.from(numbers));
    }

    private LottoTicket(LottoNumberCombination numbers) {
        this.numbers = numbers;
    }

    public LottoResult match(WinningLotto winningLotto) {
        return winningLotto.match(numbers);
    }

    public List<Integer> values() {
        return numbers.values();
    }
}
