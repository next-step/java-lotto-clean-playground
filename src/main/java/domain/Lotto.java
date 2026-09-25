package domain;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public int getMatchCount(Lotto winningLotto) {
        int count = 0;
        for (LottoNumber number : numbers) {
            count += winningLotto.match(number);
        }

        return count;
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
