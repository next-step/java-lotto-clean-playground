package domain;

import java.util.List;

public class WinningLottoNumber {
    private final Lotto winningLotto;

    public WinningLottoNumber(List<Integer> winningNumber) {
        this.winningLotto = new Lotto(winningNumber);
    }

    public int countMatches(Lotto purchasedLotto) {
        List<Integer> winningNumber = winningLotto.getNumbers();
        List<Integer> purchasedNumbers = purchasedLotto.getNumbers();

        return (int) purchasedNumbers.stream()
                .filter(number -> winningNumber.contains(number))
                .count();
    }
}
