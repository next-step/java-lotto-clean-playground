package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {

  private final List<LottoNumber> winningNumbers;

  public WinningNumbers(List<LottoNumber> numbers) {
    this.winningNumbers = new ArrayList<>(numbers);
  }

  public int countMatches(Lotto lotto) {
    return (int) winningNumbers.stream()
        .filter(lotto::contains)
        .count();
  }

  public boolean contains(LottoNumber number) {
    return winningNumbers.stream()
        .anyMatch(n -> n.isSameAs(number));
  }
}
