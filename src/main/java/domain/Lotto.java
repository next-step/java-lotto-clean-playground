package domain;

import java.util.List;

public class Lotto {
    private List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException("로또 숫자는 정렬되어야 합니다.");
        }
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException("로또 숫자는 중복될 수 없습니다.");
        }
        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public String toString() {
        return numbers.toString();
    }

    private boolean isSorted(List<LottoNumber> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i).getNumber() > numbers.get(i + 1).getNumber()) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDuplicate(List<LottoNumber> numbers) {
        return numbers.stream().map(LottoNumber::getNumber).distinct().count() != numbers.size();
    }

}
