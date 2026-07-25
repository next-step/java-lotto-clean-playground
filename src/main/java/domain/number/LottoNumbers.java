package domain.number;

import domain.lotto.WinningLotto;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    private LottoNumbers(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = sort(numbers);
    }

    public static LottoNumbers from(List<Integer> numbers) {
        return new LottoNumbers(toLottoNumbers(numbers));
    }

    public static LottoNumbers of(List<LottoNumber> numbers) {
        return new LottoNumbers(numbers);
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    private void validate(List<LottoNumber> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
    }

    private void validateCount(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        if (Set.copyOf(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private List<LottoNumber> sort(List<LottoNumber> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public MatchCount countMatching(WinningLotto winningLotto) {
        return MatchCount.from(countContainedNumbers(winningLotto));
    }

    private int countContainedNumbers(WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public void validateNotContains(LottoNumber lottoNumber) {
        if (contains(lottoNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> values() {
        return Collections.unmodifiableList(toValues());
    }

    private List<Integer> toValues() {
        return numbers.stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList());
    }

}
