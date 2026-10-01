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
                .filter(number -> winningNumbers.contains(number))
                .count();
    }

}
