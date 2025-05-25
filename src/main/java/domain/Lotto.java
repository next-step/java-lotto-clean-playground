package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("로또 번호 리스트는 null일 수 없습니다.");
        }

        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d개여야 합니다.", LOTTO_NUMBER_COUNT)
            );
        }

        if (hasDuplicateNumbers(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicateNumbers(List<LottoNumber> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

    public int countMatchingNumbers(List<LottoNumber> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public List<Integer> getNumberValues() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    public String toString() {
        return getNumberValues().toString();
    }
}
