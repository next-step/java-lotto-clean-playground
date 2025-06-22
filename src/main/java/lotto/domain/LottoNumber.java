package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {

  private static final int MIN_LOTTO_NUMBER = 1;
  private static final int MAX_LOTTO_NUMBER = 45;

  private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

  static {
    for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
      CACHE.put(i, new LottoNumber(i));
    }
  }

  private final int number;

  private LottoNumber(final int number) {
    this.number = number;
  }

  public static LottoNumber of(final int number) {
    validateNumberRange(number);
    return CACHE.get(number);
  }

  private static void validateNumberRange(final int number) {
    if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
      throw new IllegalArgumentException("로또 번호는 " + MIN_LOTTO_NUMBER + "부터 " +
          MAX_LOTTO_NUMBER + " 사이의 숫자여야 합니다.");
    }
  }

  public int number() {
    return number;
  }

  @Override
  public String toString() {
    return String.valueOf(number);
  }
}
