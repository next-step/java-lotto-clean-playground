package domain;

public class LottoGame {
	public static final int LOTTO_LENGTH = 6;
	public static final int LOTTO_MAX_NUMBER = 45;

	private final int purchaseAmount;
	private final int lottoCount;

	public LottoGame(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
		this.lottoCount = purchaseAmount / 1000;
	}

	public int getLottoCount() {
		return lottoCount;
	}
}
