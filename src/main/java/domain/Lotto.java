package domain;

import java.util.ArrayList;
import java.util.List;

public final class Lotto {
    private static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> numbers() {
        return numbers;
    }

    public int matchCount(Lotto winning) {
        int count = 0;
        for (LottoNumber n : numbers) {
            if (winning.contains(n)) count++;
        }
        return count;
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicate(List<LottoNumber> numbers) {
        List<LottoNumber> seen = new ArrayList<>();
        for (LottoNumber n : numbers) {
            if (seen.contains(n))
                return true;
            seen.add(n);
        }
        return false;
    }
}
