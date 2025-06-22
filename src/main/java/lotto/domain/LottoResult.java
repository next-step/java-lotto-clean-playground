package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoResult extends Lotto {

  public LottoResult(final List<LottoNumber> numbers) {
    super(numbers);
  }

  public static LottoResult of(final List<Integer> numbers) {
    return new LottoResult(numbers.stream().map(LottoNumber::of).toList());
  }

  public int matchCount(final Lotto userLotto) {
    List<LottoNumber> winningNumbers = this.numbers();
    List<LottoNumber> userNumbers = userLotto.numbers();

    Set<LottoNumber> winningNumbersSet = new HashSet<>(winningNumbers);

    int count = 0;
    for (LottoNumber number : userNumbers) {
      if (winningNumbersSet.contains(number)) {
        count++;
      }
    }

    return count;
  }
}
