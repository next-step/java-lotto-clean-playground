package model;

import config.ResultType;
import java.util.List;

public class WinningLotto {

    private static final int BONUS_CHECK_COUNT = 5;

    private final Numbers winningNumbers;
    private final int bonusNumber;

    public WinningLotto(final Numbers numbers, final int bonusNumber) {
        this.winningNumbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public void getLottoResult(final List<Lotto> lottos, final LottoStat stat) {
        for (Lotto lotto : lottos) {
            ResultType lottoResult = compareLotto(lotto);
            stat.updateWinningStat(lottoResult);
        }
    }

    private ResultType compareLotto(final Lotto lotto) {
        Numbers lottoNumbers = lotto.getNumbers();
        int commonCount = lottoNumbers.getCommonCount(winningNumbers);
        boolean hasBonus = false;
        if (commonCount == BONUS_CHECK_COUNT) {
            hasBonus = lottoNumbers.containBonusNumber(bonusNumber);
        }
        return ResultType.getResultType(commonCount, hasBonus);
    }

}
