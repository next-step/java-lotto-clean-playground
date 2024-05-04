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

    // List<Lotto>를 입력받아서, WinningLotto의 당첨번호와 비교
    public void getLottoResult(List<Lotto> lottos, LottoStat stat) {
        for (Lotto lotto : lottos) {
            // Lotto 1개와 WinningLotto를 비교해서 당첨번호 개수 반환 메서드 호출
            ResultType lottoResult = compareLotto(lotto);
            // 해당 번호(Key)에 해당하는 Value를 채움 - 일급 컬렉션 구현 후 추가
            stat.updateWinningStat(lottoResult);
        }
    }

    private ResultType compareLotto(Lotto lotto) {
        Numbers lottoNumbers = lotto.getNumbers();
        int commonCount = lottoNumbers.getCommonCount(winningNumbers);
        boolean hasBonus = false;
        if (commonCount == BONUS_CHECK_COUNT) {
            hasBonus = lottoNumbers.containBonusNumber(bonusNumber);
        }
        return ResultType.getResultType(commonCount, hasBonus);
    }

}
