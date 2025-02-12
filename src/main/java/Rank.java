import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    UNRANK(0, 0);

    private final int match;
    private final int reward;

    Rank(int match, int reward){
        this.match = match;
        this.reward = reward;
    }

    public static Rank getRank(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == matchCount)
                .findFirst()
                .orElse(UNRANK);
    }

    public static int getReward(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == matchCount) // matchCount와 일치하는 Rank 찾기
                .map(Rank::getReward) // 해당 Rank의 reward 값 가져오기
                .findFirst() // 첫 번째 일치하는 값 선택
                .orElse(0); // 없으면 0 반환 (UNRANK)
    }

    public int getReward() {
        return reward;
    }
}
