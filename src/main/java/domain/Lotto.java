package domain;

public class Lotto {
    private static final int moneyNumber = 1000;


    private int LottoNum(int money) {
        return money / moneyNumber;
    }

    public int getLottoNum(int money) {
        return LottoNum(money);
    }
}
