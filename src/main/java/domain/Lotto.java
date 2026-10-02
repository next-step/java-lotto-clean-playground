package domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private final Set<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        this.numbers = numbers.stream().map(LottoNumber::new).collect(Collectors.toUnmodifiableSet());
        validateDuplicates();
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers == null || numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates() {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    public int countMatches(Lotto winningLotto) {
        return (int) numbers.stream().filter(winningLotto.numbers::contains).count();
    }

    public List<Integer> getSortedNumbers() {
        return numbers.stream().sorted().map(LottoNumber::getNumber).toList();
    }

    public boolean contains(int number) {
        return numbers.contains(new LottoNumber(number));
    }
}
