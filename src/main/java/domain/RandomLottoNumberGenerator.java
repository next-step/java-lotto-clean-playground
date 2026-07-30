package domain;

import static domain.LottoRule.LOTTO_NUMBERS_COUNT;
import static domain.LottoRule.MAX_NUMBER;
import static domain.LottoRule.MIN_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator {
  private final List<Integer> lottoNumbers;
  public RandomLottoNumberGenerator() {
    lottoNumbers = new ArrayList<>();
    for(Integer i = MIN_NUMBER; i <= MAX_NUMBER; i++){
      lottoNumbers.add(i);
    }
  }
  public List<Integer> generate() {
    Collections.shuffle(lottoNumbers);
    return new ArrayList<>(lottoNumbers.subList(0, LOTTO_NUMBERS_COUNT));
  }
}
