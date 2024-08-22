package domain;

public record LottoPrice(int price, int manualNumber) {

    private static final int PRICE_UNIT = 1000;

    public LottoPrice (int price, int manualNumber){
        checkMinimum(price);
        checkPriceUnit(price);
        checkManualNumberMin(manualNumber);
        checkManualNumberMax(manualNumber);
        this.price = price;
        this.manualNumber = manualNumber;
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

    private void checkManualNumberMin(int manualNumber){
        if(manualNumber < 0){
            throw new IllegalArgumentException("수동 로또 수는 0 이상입니다.");
        }
    }

    private void checkManualNumberMax(int manualNumber){
        if(manualNumber > price / 1000){
            throw new IllegalArgumentException("금액으로 구매할 수 있는 로또 수를 초과했습니다.");
        }
    }
}