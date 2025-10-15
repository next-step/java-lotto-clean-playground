package model;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    private int purchaseAmount;

    public Money(int purchaseAmount) {
        validateMinimumAmount(purchaseAmount);
        validateAmountUnit(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validateMinimumAmount(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상 구매 가능합니다.");
        }
    }

    private void validateAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매는" + LOTTO_PRICE + "원 단위로 가능합니다");
        }
    }

    public void validateManualLottoCount(int manualLottoCount){
        if (manualLottoCount > getTotalLottoCount()) {
            throw new IllegalArgumentException("수동으로 구매할 로또의 수가 총 구매 가능 개수를 초과했습니다.");
        }
    }

    public int getTotalLottoCount(){
        return this.purchaseAmount/LOTTO_PRICE;
    }

    public int getCountOfAutoLottos(int manualLotto) {
        return (purchaseAmount / LOTTO_PRICE) - manualLotto;
    }
}
