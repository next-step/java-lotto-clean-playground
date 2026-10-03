package domain;

import java.util.List;

public class WinningLotto {
    public final Lotto winnerLotto;
    public final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers,int bonusNumber){
        winnerLotto= Lotto.from(numbers);
        this.bonusNumber=new LottoNumber(bonusNumber);
    }
    public int getCount(Lotto other) {
        return winnerLotto.getCount(other);
    }

    public boolean hasBonusNumber(Lotto other) {
        return other.contains(this.bonusNumber);
    }

}
