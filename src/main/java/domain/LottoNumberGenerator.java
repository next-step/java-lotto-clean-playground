package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator {

  private final Random random;
  private final int minNumber;
  private final int maxNumber;

  public LottoNumberGenerator(Random random, int minNumber, int maxNumber) {
    this.random = random;
    this.minNumber = minNumber;
    this.maxNumber = maxNumber;
  }

  public List<LottoNumber> generate(int lottoSize) {
    List<Integer> baseNumbers = IntStream.rangeClosed(minNumber, maxNumber)
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new));
    Collections.shuffle(baseNumbers, random);
    List<Integer> result = baseNumbers.subList(0, lottoSize);
    List<LottoNumber> lottoNumbers = result.stream()
        .map(LottoNumber::new)
        .sorted()
        .toList();
    return new ArrayList<>(lottoNumbers);
  }
}
