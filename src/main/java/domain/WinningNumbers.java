package domain;

import java.util.List;

public class WinningNumbers {
  private final Lotto lotto;

  public WinningNumbers(List<Integer> winningNumbers) {
    this.lotto = new Lotto(winningNumbers);
  }

  public long countMatch(Lotto purchasedLotto) {
    return lotto.getLottoNumbers().stream().filter(purchasedLotto.getLottoNumbers()::contains).count();
  }
}
