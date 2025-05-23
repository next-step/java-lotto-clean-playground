package domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String OUT_OF_LOTTO_NUMBER_COUNT = "로또 번호는 6개이어야 합니다.";
    private static final String LOTTO_NUMBER_CANNOT_BE_DUPLICATE = "로또 번호는 중복될 수 없습니다.";

    private final Set<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = Set.copyOf(validate(numbers));
    }

    private Set<LottoNumber> validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(OUT_OF_LOTTO_NUMBER_COUNT);
        }
        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_CANNOT_BE_DUPLICATE);
        }
        return uniqueNumbers;
    }

    public Set<LottoNumber> getNumbers() {
        return Set.copyOf(numbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public List<LottoNumber> getSortedNumbers() {
        return numbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::getLottoNumber))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
