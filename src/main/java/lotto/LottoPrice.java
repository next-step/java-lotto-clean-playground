package lotto;

public class LottoPrice {
    final int pricePerLotto = 1000;
    private final int price;
    private final int maxLottoCount;

    public LottoPrice(String priceString) {
        int price = checkPriceFormat(priceString);
        checkValidPrice(price);
        this.price = price;
        maxLottoCount = price / pricePerLotto;
    }

    public int getMaxLottoCount() {
        return maxLottoCount;
    }

    public int getPricePerLotto() {
        return pricePerLotto;
    }

    public int getPrice() {
        return price;
    }

    private void checkValidPrice(int price) {
        if (price < pricePerLotto) {
            throw new IllegalArgumentException("1000원 이상 입력해야 합니다.");
        }
    }

    // 에러 메시지로 구분?
    private int checkPriceFormat(String priceString) {
        try {
            return Integer.parseInt(priceString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

}
