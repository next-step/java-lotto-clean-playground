package domain;

public class LottoPurchaseAmount {

	public static final int MIN_PRICE = 1000;
	public static final int LOTTO_PRICE_UNIT = 1000;

	private final int purchaseAmount;

	public LottoPurchaseAmount(int price) {
		validate(price);
		this.purchaseAmount = price;
	}

	private void validate(int price) {
		if (price < MIN_PRICE) {
			throw new IllegalArgumentException("로또 구입금액은 " + MIN_PRICE + "원 보다 작을 수 없습니다.");
		}
		if (price % LOTTO_PRICE_UNIT != 0) {
			throw new IllegalArgumentException("로또 구입금액은 " + LOTTO_PRICE_UNIT + "원 단위입니다.");
		}
	}

	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	public int getLottoCount() {
		return purchaseAmount / LOTTO_PRICE_UNIT;
	}
}
