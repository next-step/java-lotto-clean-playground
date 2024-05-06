package model;

import config.ResultType;
import java.util.List;

public class WinningLotto {

    private static final int BONUS_CHECK_COUNT = 5;

    private final Numbers winningNumbers;
    private final int bonusNumber;

    public WinningLotto(final Numbers numbers, final int bonusNumber) {
        validate(numbers, bonusNumber);
        this.winningNumbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(final Numbers numbers, final int bonusNumber) {
        validateCommonElements(numbers, bonusNumber);
    }

    private void validateCommonElements(final Numbers numbers, final int bonusNumber) {
        if (numbers.containBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 로또 번호와 겹쳐선 안됩니다.");
        }
    }

    public void setLottoResult(final List<Lotto> lottos, final LottoStat stat) {
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
