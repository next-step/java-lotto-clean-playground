package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

  private static final int LOTTO_SIZE = 6;

  private final List<LottoNumber> numbers;

  public Lotto(final List<LottoNumber> numbers) {
    validateSize(numbers);
    validateNoDuplicates(numbers);
    this.numbers = new ArrayList<>(numbers);
  }

  public static Lotto of(final List<Integer> numbers) {
    return new Lotto(numbers.stream().map(LottoNumber::of).toList());
  }

  public String toString() {
    return numbers.toString();
  }

  public int size() {
    return numbers.size();
  }

  public List<LottoNumber> numbers() {
    return new ArrayList<>(numbers);
  }

  private static void validateSize(List<?> numbers) {
    if (numbers.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
    }
  }

  private static void validateNoDuplicates(List<LottoNumber> items) {
    Set<LottoNumber> uniqueItems = new HashSet<>(items);
    if (uniqueItems.size() != items.size()) {
      throw new IllegalArgumentException("로또 번호에 중복된 숫자가 있습니다.");
    }
  }
}
