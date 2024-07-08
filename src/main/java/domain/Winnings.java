package domain;

import java.util.Arrays;

public enum Winnings {
    FOURTH_PLACE(3, 5_000),
    THIRD_PLACE(4, 50_000),
    SECOND_PLACE(5, 1_500_000),
    FIRST_PLACE(6, 2_000_000_000);

    private final int matchCount;
    private final int winningPrize;

    Winnings(int matchCount, int winningPrize) {
        this.matchCount = matchCount;
        this.winningPrize = winningPrize;
    }

    public static Winnings getWinnings(int matchCount) {
        return Arrays.stream(values())
            .filter(winnings -> winnings.matchCount == matchCount)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 당첨 등수가 없습니다."));
    }

    public static Winnings of(int matchCount){
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
