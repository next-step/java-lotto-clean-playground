package model;

import java.util.List;


public class Lotto {

    private final static int PRICE = 1000;

    private final List<Integer> lottoNumber;
    private int collectedCount;

    private boolean bonusCorrect;

    public Lotto(final List<Integer> lottoNumber) {

        this.lottoNumber = lottoNumber;
        collectedCount = 0;
        bonusCorrect = false;
    }

    public void updateCollectedCount(final int collectedCount) {
        this.collectedCount = collectedCount;
    }

    public void updateBonusCorrect() {
        bonusCorrect = true;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    public static int getPrice() {
        return PRICE;
    }

    public int getCollectedCount() {
        return collectedCount;
    }

    public boolean isBonusCorrect() {
        return bonusCorrect;
    }
}
