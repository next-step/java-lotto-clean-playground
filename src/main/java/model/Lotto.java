package model;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lotto(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.numbers = numbers;
    }

    public int countMatching(List<LottoNumber> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }


}
