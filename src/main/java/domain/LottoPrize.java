package domain;

import java.util.Arrays;

public enum LottoPrize {

	LAST_PRIZE(0, false, 0),
	FIFTH_PRIZE(3, false, 5_000),
	FOURTH_PRIZE(4, false, 50_000),
	THIRD_PRIZE(5, false, 1_500_000),
	SECOND_PRIZE(5, true, 30_000_000),
	FIRST_PRIZE(6, false, 2_000_000_000),
	;

	public static final int LOTTO_PRIZE_COUNT = 5;
	private static final int LOTTO_BONUS_PRIZE = 5;

	private final int matchCount;
	private final boolean isContainsBonus;
	private final int price;

	LottoPrize(int matchCount, boolean isContainBonus, int price) {
		this.matchCount = matchCount;
		this.isContainsBonus = isContainBonus;
		this.price = price;
	}

	public static LottoPrize of(int matchCount, boolean isContainBonus) {
		if (matchCount == LOTTO_BONUS_PRIZE) {
			return Arrays.stream(LottoPrize.values())
				.filter(prize -> prize.matchCount == matchCount && prize.isContainsBonus == isContainBonus)
				.findFirst()
				.orElse(LAST_PRIZE);
		}
		return Arrays.stream(LottoPrize.values())
			.filter(prize -> prize.matchCount == matchCount)
			.findFirst()
			.orElse(LAST_PRIZE);
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean isContainsBonus() {
		return isContainsBonus;
	}

	public int getPrice() {
		return price;
	}
}
