import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final Set<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 정확히 6개여야 합니다.");
        }
        this.numbers = numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
        if (this.numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
