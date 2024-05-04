package model;

import java.util.List;


public class Lotto {

    private static final int PRICE = 1000;

    private final List<Integer> lottoNumber;

    public Lotto(final List<Integer> lottoNumber) {

        this.lottoNumber = lottoNumber;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    public static int getPrice() {
        return PRICE;
    }
}
