package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public int countMatches(Lotto winningLotto) {
        int matchCount = 0;
        for (LottoNumber number : numbers) {
            matchCount += winningLotto.match(number);
        }
        return matchCount;
    }

    private int match(LottoNumber number) {
        if (numbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
