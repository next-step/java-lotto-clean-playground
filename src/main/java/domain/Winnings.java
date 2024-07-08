package domain;

import java.util.Arrays;

public enum Winnings {
    FIFTH_PLACE(3, 5_000, false),
    FOURTH_PLACE(4, 50_000, false),
    THIRD_PLACE(5, 1_500_000, false),
    SECOND_PLACE(5, 30_000_000, true),
    FIRST_PLACE(6, 2_000_000_000, false);

    private final int matchCount;
    private final int winningPrize;
    private final boolean bonusNumber;

    Winnings(int matchCount, int winningPrize, boolean bonusNumber) {
        this.matchCount = matchCount;
        this.winningPrize = winningPrize;
        this.bonusNumber = bonusNumber;
    }

    public static Winnings getWinnings(int matchCount, boolean bonusNumber) {
        return Arrays.stream(values())
            .filter(winnings -> winnings.matchCount == matchCount)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 당첨 등수가 없습니다."));
    }

    public static Winnings of(int matchCount, boolean bonusNumber){
        if (matchCount == 5) {
            return Arrays.stream(values())
                .filter(winnings -> winnings.bonusNumber == bonusNumber)
                .findAny()
                .get();
        }
        return Arrays.stream(Winnings.values())
            .filter(winning -> winning.matchCount == matchCount)
            .findAny()
            .orElseThrow();
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public int getWinningPrize() {
        return this.winningPrize;
    }
}
