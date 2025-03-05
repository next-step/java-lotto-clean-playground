package model;

public class LottoPurchasePrice {
    private final int lottoPurchasePrice;

    public LottoPurchasePrice(int lottoPrice) {
        validateLottoPrice(lottoPrice);
        this.lottoPurchasePrice = lottoPrice;
    }

    private void validateLottoPrice(int lottoPurchasePrice) {
        if (lottoPurchasePrice < 0) {
            throw new IllegalArgumentException("로또 총 구입금액은 음수가 될 수 없습니다.");
        }

        if (lottoPurchasePrice % LottoConstants.PRICE.getValue() != 0) {
            throw new IllegalArgumentException("로또 총 구입금액은 " + LottoConstants.PRICE.getValue() +
                    "로 나누어 떨어져야 합니다.");
        }
    }

    public int getValue() {
        return lottoPurchasePrice;
    }

    public int getLottoCount() {
        return lottoPurchasePrice / LottoConstants.PRICE.getValue();
    }
}
