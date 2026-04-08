package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;
    private static final int LOTTO_SIZE = 6;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(sortNumbers(numbers));
    }

    public int countMatch(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    private void validate(List<LottoNumber> numbers) {
        validateLottoSize(numbers);
        validateDuplicate(numbers);
    }

    private List<LottoNumber> sortNumbers(List<LottoNumber> numbers) {
        return numbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::number))
                .toList();
    }

    private void validateLottoSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자의 갯수는 6개입니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
