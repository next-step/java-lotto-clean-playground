package domain.winning;

import domain.purchase.Lotto;
import domain.purchase.Lottos;

/**
 * 당첨 번호 보관 및 구매한 로또와 비교
 */
public class WinningLotto {
    private final Lotto lotto;

    public WinningLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public LottoResult match(Lottos lottos) {
        return new LottoResult(lottos.countMatches(lotto));
    }
}
