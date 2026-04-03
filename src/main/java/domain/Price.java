package domain;

public class Price {
    private final int price;

    public Price(int price) {
        validatePrice(price);
        this.price = price;
    }

    public int getBuyableLottoCount() {
        return price / 1000;
    }

    public double calculateProfitRate(int totalProfit) {
        return (double) totalProfit / price;
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        if (price < 1000) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("돈이 1000원 단위여야 합니다.");
        }
    }
}
