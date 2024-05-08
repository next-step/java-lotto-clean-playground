package domain.lotto;

public class LottoPrice {
    private static final int PRICE_UNIT = 1000;
    private static final int MINIMUM_PRICE = 999;
    private final int buyingMoney;

    public LottoPrice(int buyingMoney) {
        this.buyingMoney = validate(buyingMoney);
    }

    public int validate(int buyingMoney) {
        validateRage(buyingMoney);
        validateUnit(buyingMoney);
        return buyingMoney;
    }

    public void validateRage(int buyingMoney) {
        if(buyingMoney <= MINIMUM_PRICE)
            throw new IllegalArgumentException("천원 미만의 돈을 입력받을 수 없습니다.");
    }

    public void validateUnit(int buyingMoney) {
        if(buyingMoney % MINIMUM_PRICE != 0)
            throw new IllegalArgumentException("돈은 천원 단위로만 입력 가능합니다.");
    }
}
