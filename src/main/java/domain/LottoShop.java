package domain;

public class LottoShop {
    private static final int PRICE = 1000;
    public int calculateLottoCount(int amount){
        return amount / PRICE;
    }

    public void countLottoByAmount(int amount) {
        if (amount % (PRICE) != 0) {
            throw new IllegalArgumentException("Lotto의 가격은 1000원입니다.");
        }
    }
}
