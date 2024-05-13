package domain;

import java.util.Arrays;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUM = 1000;
    private static final int LOTTO_RANGE = 45;

    private int lottoNum(int money) {
        return money / LOTTO_NUM;
    }

    public int getLottoNum(int money) {
        return lottoNum(money);
    }

    private int lottoRandomNum() {
        return (int) (Math.random() * LOTTO_RANGE);
    }

}
