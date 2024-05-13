package domain;

public class Lotto {

    private static final int LOTTO_NUM = 1000;

    private int lottoNum(int money) {
        return money / LOTTO_NUM;
    }

    public int getLottoNum(int money) {
        return lottoNum(money);
    }
}
