package domain;

import java.util.*;

public class WinningNumbers {
    //로또 당첨 숫자
    private final Lotto winningNumbers;

    public WinningNumbers(String input) {
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();

        this.winningNumbers = new Lotto(numbers);
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }
}
