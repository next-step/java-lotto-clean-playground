package lotto.domain;
import java.util.List;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto(List<Integer> values) {
        this.lottoNumbers = new LottoNumbers(values);
    }

    public List<Integer> getValues() {
        return lottoNumbers.getNumberValues();
    }

    public int countMatch(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.getLottoNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(WinningNumbers winningNumbers) {
        return lottoNumbers.getLottoNumbers()
                .stream()
                .anyMatch(winningNumbers::matchesBonusNumber);
    }
}
