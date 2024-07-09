package lotto.domain;

import static lotto.domain.LottoRate.NONE;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class AnswerLotto {

    private final List<Integer> numbers;
    private final int bonusNumber;

    public AnswerLotto(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public Map<LottoRate, Integer> calculateResult(List<Lotto> lottos) {
        Map<LottoRate, Integer> result = new EnumMap<>(LottoRate.class);
        for (Lotto lotto : lottos) {
            AnswerMatchModel matchModel = getMatchModel(lotto);
            LottoRate rate = LottoRate.from(matchModel);
            if (rate != NONE) {
                result.put(rate, result.getOrDefault(rate, 0) + 1);
            }
        }
        return result;
    }

    private AnswerMatchModel getMatchModel(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int count = (int)lottoNumbers.stream()
            .filter(numbers::contains)
            .count();
        boolean isBonus = lottoNumbers.contains(bonusNumber);
        if (isBonus) {
            return new AnswerMatchModel(count + 1, true);
        }
        return new AnswerMatchModel(count, false);
    }
}
