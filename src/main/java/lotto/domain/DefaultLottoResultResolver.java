package lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class DefaultLottoResultResolver {
    private WinningLotto win;

    private Set<LottoNumber> rowNumbers;
    private int matchingCount;

    public LottoResult getResult(WinningLotto win, Lotto lottoRow) {
        this.win = win;

        rowNumbers = new HashSet<>(lottoRow.getNumbers());

        Set<LottoNumber> matchingNumbers = new HashSet<>(win.getNumbers());
        matchingNumbers.retainAll(rowNumbers);
        matchingCount = matchingNumbers.size();

        return getResult();
    }

    private LottoResult getResult() {
        if (matchingCount == 6) {
            return LottoResult.SIX;
        }
        if (matchingCount == 5) {
            return getSecondResult();
        }
        return getGeneralResult();
    }

    private LottoResult getSecondResult() {
        LottoNumber bonus = win.bonus();
        if (rowNumbers.contains(bonus)) {
            return LottoResult.FIVE_BONUS;
        }
        return LottoResult.FIVE;
    }

    private LottoResult getGeneralResult() {
        if (matchingCount == 4) {
            return LottoResult.FOUR;
        }
        if (matchingCount == 3) {
            return LottoResult.THREE;
        }
        return LottoResult.NONE;
    }
}
