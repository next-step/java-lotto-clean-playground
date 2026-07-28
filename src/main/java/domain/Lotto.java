package domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers.size());
        Set<LottoNumber> sorted = new TreeSet<>(numbers);
        validateDuplicate(numbers.size(), sorted.size());
        this.numbers = sorted;
    }

    public static Lotto from(List<String> rawNumbers) {
        try {
            return new Lotto(toLottoNumbers(rawNumbers));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자로 입력해야 합니다.");
        }
    }

    private void validateSize(int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(int inputSize, int uniqueSize) {
        if (uniqueSize != inputSize) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private static List<LottoNumber> toLottoNumbers(List<String> rawNumbers) {
        return rawNumbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::of)
                .toList();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }
}
