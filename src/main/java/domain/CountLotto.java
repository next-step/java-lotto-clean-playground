package domain;

public class CountLotto {

    private static final int LOTTO_ADD_PRICE = 1000;

    public int getCountLotto(int money) {
        return countLotto(money);
    }

    private int countLotto(int money) {
        return money / LOTTO_ADD_PRICE;
    }
}
