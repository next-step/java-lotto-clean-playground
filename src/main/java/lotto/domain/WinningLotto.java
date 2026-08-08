package lotto.domain;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(
            Lotto lotto,
            LottoNumber bonusNumber
    ) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(
                    "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
            );
        }
    }

    public Rank determineRank(Lotto purchasedLotto) {
        int matchingCount = purchasedLotto.countMatchingNumbers(lotto);
        boolean bonusMatched = purchasedLotto.contains(bonusNumber);

        return Rank.find(matchingCount, bonusMatched);
    }
}
