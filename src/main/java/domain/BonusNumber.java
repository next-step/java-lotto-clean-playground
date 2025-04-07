package domain;

public class BonusNumber {

  private final LottoNumber bonusNumber;

  public BonusNumber(LottoNumber number, WinningNumbers winningNumbers) {
    validateNotInLotto(number, winningNumbers);
    this.bonusNumber = number;
  }

  private void validateNotInLotto(LottoNumber number, WinningNumbers winningNumbers) {
    if (winningNumbers.contains(number)) {
      throw new IllegalArgumentException("보너스 번호는 로또 번호와 중복될 수 없습니다.");
    }
  }

  public boolean isSameAs(Lotto lotto) {
    return lotto.contains(bonusNumber);
  }
}
