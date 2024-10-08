package domain;

import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumber;
    private int matchLottoNumber;

    public Lotto(final List<Integer> lotto) {
        this.lottoNumber = lotto;
        this.matchLottoNumber = 0;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    public int getMatchLottoNumber() {
        return matchLottoNumber;
    }

    public void checkContainedWinningLottoNumbers(final int winningLottoNumber) {
        if (lottoNumber.contains(winningLottoNumber)) {
            matchLottoNumber += 1;
        }
    }
}
