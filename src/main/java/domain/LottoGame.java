package domain;

public class LottoGame {

	private final LottoPurchaseAmount purchaseAmount;

	public LottoGame(int purchaseAmount) {
		this.purchaseAmount = new LottoPurchaseAmount(purchaseAmount);
	}

	public int getLottoCount() {
		return purchaseAmount.getLottoCount();
	}

	public int getPurchaseAmount() {
		return purchaseAmount.getPurchaseAmount();
	}
}
