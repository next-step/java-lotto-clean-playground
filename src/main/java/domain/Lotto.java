package domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class Lotto {
    private final SortedSet<LottoNumber> numbers;

    public Lotto(SortedSet<LottoNumber> numbers) {
        validateSize(numbers);
        this.numbers = new TreeSet<>(numbers);
    }

    private void validateSize(SortedSet<LottoNumber> numbers) {
        if (numbers.size() != createList.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 하며, 중복될 수 없습니다.");
        }
    }

    public int contains(LottoNumber number) {
        if (numbers.contains(number)) return 1;
        return 0;
    }

    public SortedSet<LottoNumber> getNumbers() {
        return new TreeSet<>(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
