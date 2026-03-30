package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public int countMatch(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto.numbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
