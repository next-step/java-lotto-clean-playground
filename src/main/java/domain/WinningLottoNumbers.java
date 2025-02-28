package domain;

import java.util.List;

public class WinningLottoNumbers {
    private final List<LottoNumber> numbers;

    public WinningLottoNumbers(List<Integer> numbers) {
        this.numbers = numbers.stream()
                .map(LottoNumber::of)
                .toList();
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
