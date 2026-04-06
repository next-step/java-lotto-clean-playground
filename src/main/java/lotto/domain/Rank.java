package lotto.domain;

import java.util.Arrays;

public enum Rank { //열거형. 정해진 값들만 쓰게 만드는 타입
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int matchCount; //몇개 일치?
    private final int winningMoney; //상금

    Rank(int matchCount, int winningMoney) { //생성자
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) { //일치하는 수를 등수로 바꿔주는 함수
        if (matchCount == 5) return matchBonus ? SECOND : THIRD;
        return Arrays.stream(values())
                .filter(rank -> rank != SECOND && rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
