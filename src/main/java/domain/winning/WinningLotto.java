package domain.winning;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;

    public WinningLotto(List<Integer> winningNumbers) {
        List<LottoNumber> lottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .toList();
        this.winningLotto = new Lotto(lottoNumbers);
    }

    public int countMatches(Lotto purchasedLotto) {
        List<Integer> winningNumbers = winningLotto.getNumbers();
        List<Integer> purchasedNumbers = purchasedLotto.getNumbers();

        return (int) purchasedNumbers.stream()
                .filter(number -> winningNumbers.contains(number))
                .count();
    }

    public List<Integer> getWinningNumbers() {
        return winningLotto.getNumbers();
    }
}
