package domain.winning;

import domain.purchase.Lotto;
import domain.purchase.LottoNumber;
import domain.purchase.Lottos;
import java.util.ArrayList;
import java.util.List;

/**
 * 당첨 번호와 보너스 볼 보관, 구매한 로또의 등수 판정
 */
public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank rankOf(Lotto purchasedLotto) {
        int matchCount = purchasedLotto.countMatches(lotto);
        boolean bonusMatched = purchasedLotto.contains(bonusNumber);
        return LottoRank.from(matchCount, bonusMatched);
    }

    public LottoResult match(Lottos lottos) {
        return new LottoResult(lottos.map(this::rankOf));
    }
}
