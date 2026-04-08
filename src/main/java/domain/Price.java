package domain;

public class Price {
    public static final int priceOfOneLotto = 1000;

    private final int price;

    public Price(int price) {
        validatePrice(price);
        this.price = price;
    }

    public int getBuyableLottoCount() {
        return price / priceOfOneLotto;
    }

    public double calculateProfitRate(int totalProfit) {
        return (double) totalProfit / price;
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
        if (price < priceOfOneLotto) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        if (price % priceOfOneLotto != 0) {
            throw new IllegalArgumentException("돈이 " + priceOfOneLotto + "원 단위여야 합니다.");
        }
    }
}
