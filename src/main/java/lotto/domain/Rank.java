package lotto.domain;

import java.util.Arrays;

public enum Rank { //열거형. 정해진 값들만 쓰게 만드는 타입
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    FIRST(6, 2_000_000_000);

    private final int matchCount; //몇개 일치?
    private final int winningMoney; //상금

    Rank(int matchCount, int winningMoney) { //생성자
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int matchCount) { //일치하는 수를 등수로 바꿔주는 함수
        return Arrays.stream(values()) //values는 enum의 모든 값을 가져온다
                .filter(rank -> rank.matchCount == matchCount)//matchCount가 같은 등수만 남겨라
                .findFirst() //첫번째 결과 가져오기
                .orElse(MISS); //못찾으면 MISS 반환
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
