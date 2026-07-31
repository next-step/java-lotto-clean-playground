package domain;

import java.util.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    public static Lotto from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }

    public int countMatchingNumbers(Lotto other) {
        return (int) numbers.stream()
                .filter(other::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    private List<LottoNumber> sortNumbers(List<LottoNumber> numbers) {
        List<LottoNumber> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(Comparator.comparingInt(LottoNumber::getValue));
        return sortedNumbers;
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
