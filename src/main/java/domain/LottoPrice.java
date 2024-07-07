package domain;

public record LottoPrice(int price) {

    private static final int PRICE_UNIT = 1000;

    public LottoPrice{
        checkMinimum(price);
        checkPriceUnit(price);
    }

    private void checkPriceUnit(int price){
        if(price%PRICE_UNIT != 0){
            throw new IllegalArgumentException("금액은 1000원 단위입니다.");
        }
    }

    private void checkMinimum(int price){
        if(price < 1000){
            throw new IllegalArgumentException("금액은 1000원 이상입니다.");
        }
    }
}
