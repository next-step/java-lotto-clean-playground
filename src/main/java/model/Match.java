package model;

public enum Match {
    MATCH_3(3, 0),  // 3개 일치
    MATCH_4(4, 0),  // 4개 일치
    MATCH_5(5, 0),  // 5개 일치
    MATCH_5_BONUS(5, 1),  // 5개 일치 + 보너스 볼 일치
    MATCH_6(6, 0);   // 6개 일치

    private final int match;  // 로또 번호 일치 개수
    private final int bonusMatch;  // 보너스 볼 일치 여부 (0: 불일치, 1: 일치)

    Match(int match, int bonusMatch) {
        this.match = match;
        this.bonusMatch = bonusMatch;
    }

    public int getMatch() {
        return match;
    }

    public int getBonusMatch() {
        return bonusMatch;
    }
}