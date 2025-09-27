package domain;

import java.util.List;

public class Lotto {
    private List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public String toString() {
        return numbers.toString();
    }
}
