package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

  private static final int LOTTO_SIZE = 6;

  public final List<LottoNumber> numbers;

  private Lotto(List<LottoNumber> numbers) {
    validate(numbers);
    this.numbers = new ArrayList<>(numbers);
  }

  public static Lotto createAuto(LottoNumberGenerator lottoNumberGenerator) {
    List<LottoNumber> generatedNumbers = lottoNumberGenerator.generate(LOTTO_SIZE);
    validate(generatedNumbers);
    return new Lotto(generatedNumbers);
  }

  public static Lotto createManual(List<LottoNumber> manualNumbers) {
    validate(manualNumbers);
    return new Lotto(manualNumbers);
  }

  private static void validate(List<LottoNumber> numbers) {
    validateSize(numbers);
    validateDuplicates(numbers);
  }

  private static void validateSize(List<LottoNumber> numbers) {
    if (numbers.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개여야 합니다.");
    }
  }

  private static void validateDuplicates(List<LottoNumber> numbers) {
    boolean hasDuplicate = numbers.stream()
        .anyMatch(number -> numbers.stream()
            .anyMatch(otherNumber -> number.isSameAs(otherNumber) && number != otherNumber));

    if (hasDuplicate) {
      throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
    }
  }

  public boolean contains(LottoNumber number) {
    return numbers.stream().anyMatch(n -> n.isSameAs(number));
  }

  public List<LottoNumber> getNumbers() {
    return new ArrayList<>(numbers);
  }

  @Override
  public String toString() {
    return numbers.stream()
        .map(LottoNumber::toString)
        .collect(Collectors.joining(", "));
  }
}
