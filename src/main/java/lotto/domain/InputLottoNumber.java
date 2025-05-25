package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class InputLottoNumber {
    private final List<LottoNumber> numbers;

    private InputLottoNumber(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }
  
    public static InputLottoNumber of(List<LottoNumber> list) {
        return new InputLottoNumber(list);
    }

    public int countMatching(WinningNumber winning) {
        return (int) numbers.stream()
                .filter(winning::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(n -> String.valueOf(n.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
