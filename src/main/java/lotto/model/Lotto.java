package lotto.model;

import java.util.List;

public class Lotto {

    public static final int PRICE = 1000;
    private final List<LottoNumbers> lottoNumbers;

    public Lotto(List<LottoNumbers> lottoNumbers) {
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public List<LottoNumbers> getNumbers() {
        return lottoNumbers;
    }

    public WinningResult checkWinning(WinningNumbers winningNumbers) {
        return new WinningResult(winningNumbers, this.lottoNumbers);
    }
}
