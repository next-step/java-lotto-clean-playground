package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<LottoNumber> numbers;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public static Lotto from(List<LottoNumber> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
        return new Lotto(numbers);
    }

    private static void validateCount(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 개수가 6개여야 합니다.");
        }
    }

    private static void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public int countLotto(Lotto lotto) {
        int count = 0;

        for (LottoNumber number : numbers) {
            if (lotto.contains(number)) count++;
        }
        return count;
    }
}
