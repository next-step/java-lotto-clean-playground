package lotto;

import java.util.List;


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

    @Override
    public String toString() {
        return numbers.toString();
    }
}
