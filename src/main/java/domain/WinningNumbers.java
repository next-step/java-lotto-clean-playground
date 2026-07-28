package domain;

import java.util.List;

public class WinningNumbers {
  private final Lotto lotto;

  public WinningNumbers(List<Integer> winningNumbers) {
    this.lotto = new Lotto(winningNumbers);
  }

  public Lotto getLotto(){
    return lotto;
  }
}
