package model;

import java.util.ArrayList;
import java.util.List;


public class Lotto {

    private final static int PRICE = 1000;

    private final List<Integer> lottoNumber;
    private int collectedCount;

    public Lotto(final List<Integer> lottoNumber) {

        this.lottoNumber = lottoNumber;
        collectedCount = 0;
    }

    public void setCollectedCount(final int collectedCount) {
        this.collectedCount=collectedCount;
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
}
