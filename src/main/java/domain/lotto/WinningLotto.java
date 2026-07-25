package domain.lotto;

import domain.number.LottoNumberCombination;
import domain.result.LottoResult;
import domain.result.MatchCount;
import java.util.List;
import java.util.Optional;

public class WinningLotto {
    private final LottoNumberCombination numbers;
    private final BonusBall bonusBall;

    private WinningLotto(LottoNumberCombination numbers, BonusBall bonusBall) {
        this.numbers = numbers;
        if (bonusBall != null) {
            numbers.validateNotContains(bonusBall.lottoNumber());
        }
        this.bonusBall = bonusBall;
    }

    public static WinningLotto from(List<Integer> numbers) {
        return new WinningLotto(LottoNumberCombination.from(numbers), null);
    }

    public static WinningLotto of(List<Integer> numbers, BonusBall bonusBall) {
        return new WinningLotto(LottoNumberCombination.from(numbers), bonusBall);
    }

    public static WinningLotto from(LottoNumberCombination numbers) {
        return new WinningLotto(numbers, null);
    }

    public static WinningLotto of(LottoNumberCombination numbers, BonusBall bonusBall) {
        return new WinningLotto(numbers, bonusBall);
    }

    public Optional<BonusBall> bonusBall() {
        return Optional.ofNullable(bonusBall);
    }

    public MatchCount countMatching(LottoTicket lottoTicket) {
        return lottoTicket.countMatching(numbers);
    }

    public LottoResult match(LottoTicket lottoTicket) {
        MatchCount matchCount = countMatching(lottoTicket);
        return LottoResult.of(matchCount, isBonusBallMatched(lottoTicket));
    }

    private boolean isBonusBallMatched(LottoTicket lottoTicket) {
        return bonusBall()
                .map(bonusBall -> lottoTicket.contains(bonusBall.lottoNumber()))
                .orElse(false);
    }
}
