package lotto.domain;

import static lotto.domain.LottoRate.NONE;
import static lotto.domain.LottoRate.from;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class AnswerLotto {

    private final List<Integer> numbers;

    public AnswerLotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Map<LottoRate, Integer> calculateResult(List<Lotto> lottos) {
        Map<LottoRate, Integer> result = new EnumMap<>(LottoRate.class);
        for (Lotto lotto : lottos) {
            int count = getMatchCount(lotto);
            LottoRate rate = from(count);
            if (rate != NONE) {
                result.put(rate, result.getOrDefault(rate, 0) + 1);
            }
        }
        return result;
    }

    private int getMatchCount(Lotto lotto) {
        return (int)lotto.getNumbers().stream()
            .filter(numbers::contains)
            .count();
    }
}
