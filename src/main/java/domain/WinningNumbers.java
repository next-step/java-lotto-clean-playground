package domain;

import static domain.LottoRule.MAX_NUMBER;
import static domain.LottoRule.MIN_NUMBER;

import java.util.List;

public class WinningNumbers {
  private final Lotto lotto;
  private final int bonusNumber;

  public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
    validate(winningNumbers, bonusNumber);
    this.lotto = new Lotto(winningNumbers);
    this.bonusNumber = bonusNumber;
  }

  private void validate(List<Integer> winningNumbers, int bonusNumber) {
    validateBonusNumberRange(bonusNumber);
    if(winningNumbers.contains(bonusNumber)){
      throw new IllegalArgumentException("당첨번호에 존재하는 보너스 번호는 존재할 수 없습니다.");
    }
  }

  private void validateBonusNumberRange(int bonusNumber) {
    if(bonusNumber > MAX_NUMBER || bonusNumber < MIN_NUMBER){
      throw new IllegalArgumentException("로또 번호의 범위는 " + MIN_NUMBER + "보다 작거나, " + MAX_NUMBER + "보다 클 수 없습니다.");
    }
  }

  public long countMatch(Lotto purchasedLotto) {
    return lotto.countMatchingNumber(purchasedLotto);
  }

  public boolean hasBonusNumber(Lotto purchasedLotto) {
    return purchasedLotto.isMatchingBonusNumber(bonusNumber);
  }
}
