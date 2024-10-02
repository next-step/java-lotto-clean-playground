package domain;

import java.util.List;

public class BonusNumber {
    private Lottos lottos;
    private final int bonusNumber;

    public BonusNumber(int bonusNumber){
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber(){
        return bonusNumber;
    }

    public boolean match(Lotto lotto) {
        return lotto.getLotto().contains(bonusNumber);
    }
}
