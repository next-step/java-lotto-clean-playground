package domain;

public enum Ranking {

    FIRST(6, 0, 2000000000, "6개 일치 1등입니다."),
    SECOND(5, 1, 30000000, "5개 일치, 보너스 볼 일치 2등입니다."),
    THIRD(5, 0, 1500000, "5개 일치 3등입니다."),
    FOURTH(4, 0, 50000, "4개 일치 4등입니다."),
    FIFTH(3, 0, 5000, "3개 일기 5등입니다."),
    ERROR(0, 0, 0, "");

    private final int countOfRanking;
    private final int bonusCountOfRanking;
    private final int price;
    private final String message;

    Ranking(int countOfRanking, int bonusCountOfRanking, int price, String message) {
        this.countOfRanking = countOfRanking;
        this.bonusCountOfRanking = bonusCountOfRanking;
        this.price = price;
        this.message = message;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public static Ranking valueOfCount(int countOfRanking, int bonusCountOfRanking) {
        for (Ranking ranking : Ranking.values()) {
            Ranking rankings = getRanking(countOfRanking, bonusCountOfRanking, ranking);
            if (rankings != null) return rankings;
        }
        return ERROR;
    }

    private static Ranking getRanking(int countOfRanking, int bonusCountOfRanking, Ranking ranking) {
        if (countOfRanking == ranking.countOfRanking && bonusCountOfRanking == ranking.bonusCountOfRanking) {
            return ranking;
        }
        return null;
    }
}
