package Model;

import static Model.Constants.MIN_PRICE;
import static Model.Constants.UNIT;

public class LottoPrice {
    private int price;

    public LottoPrice(int price) {
        validate(price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    private void validate(int price) {
        validateUnit(price);
        validateMin(price);
    }

    private void validateMin(int price) {
        if (price < MIN_PRICE) throw new IllegalArgumentException("값이 0원 미만일 수는 없습니다.");
    }

    private void validateUnit(int price) {
        if (price % UNIT != 0) throw new IllegalArgumentException("가격은 1000원 단위이어야 합니다.");
    }
}
