package domain;

public class Price {
    public static final int PRICE_OF_ONE_LOTTO = 1000;

    private final int price;

    public Price(int price) {
        validatePrice(price);
        this.price = price;
    }

    public int getBuyableLottoCount() {
        return price / PRICE_OF_ONE_LOTTO;
    }

    public double calculateProfitRate(int totalProfit) {
        return (double) totalProfit / price;
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
        if (price < PRICE_OF_ONE_LOTTO) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        if (price % PRICE_OF_ONE_LOTTO != 0) {
            throw new IllegalArgumentException("돈이 " + PRICE_OF_ONE_LOTTO + "원 단위여야 합니다.");
        }
    }
}
