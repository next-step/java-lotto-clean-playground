package model;

import java.util.*;

public class WinningLottoInfo {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public WinningLottoInfo(Lotto lotto, BonusNumber bonusNumber) {
        validateWinningLottoInfo(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLottoInfo(Lotto lotto, BonusNumber bonusNumber) {
        lotto.getValue().forEach((lottoNumber) -> {
           if (lottoNumber == bonusNumber.getValue()) {
               throw new IllegalArgumentException("로또 번호와 보너스 넘버는 서로 중복될 수 없습니다.");
           }
        });
    }

    public List<Integer> getLottoValue() {
        return lotto.getValue();
    }

    public int getBonusNumberValue() {
        return bonusNumber.getValue();
    }
}
