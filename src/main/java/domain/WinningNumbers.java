package domain;

import java.util.*;

public class WinningNumbers {
    //로또 당첨 숫자
    private final List<LottoNumber> winningNumbers;

    public WinningNumbers(String input) {
        this.winningNumbers = Arrays.stream(input.split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
    }

    public List<LottoNumber> getWinningNumbers() {
        return winningNumbers;
    }
}
