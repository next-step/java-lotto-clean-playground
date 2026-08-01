package domain.lotto;

import domain.number.LottoNumberCombination;
import domain.result.LottoResult;
import domain.result.MatchCount;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final LottoNumberCombination numbers;
    private final BonusBall bonusBall;

    private WinningLotto(LottoNumberCombination numbers, BonusBall bonusBall) {
        this.numbers = numbers;
        Objects.requireNonNull(bonusBall);
        bonusBall.validateNotDuplicatedWith(numbers);
        this.bonusBall = bonusBall;
    }

    public static WinningLotto of(List<Integer> numbers, BonusBall bonusBall) {
        return new WinningLotto(LottoNumberCombination.from(numbers), bonusBall);
    }

    LottoResult match(LottoNumberCombination ticketNumbers) {
        MatchCount matchCount = countMatching(ticketNumbers);
        return LottoResult.of(matchCount, isBonusBallMatched(ticketNumbers));
    }

    private MatchCount countMatching(LottoNumberCombination ticketNumbers) {
        return ticketNumbers.countMatching(numbers);
    }

    private boolean isBonusBallMatched(LottoNumberCombination ticketNumbers) {
        return ticketNumbers.contains(bonusBall.lottoNumber());
    }
}
