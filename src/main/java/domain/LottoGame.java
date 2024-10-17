package domain;

import java.util.List;

public class LottoGame {

    public static int matchNum(List<String> Lotto, List<String> LastWeekLotto) {
        return (int) Lotto.stream().filter(LastWeekLotto::contains).count();
    }

    public static int matchBonus(List<String> Lotto, String BonusBall) {
        return Lotto.contains(BonusBall) ? 1 : 0;
    }

    public static Rank getRank(List<String> Lotto, List<String> LastWeekLotto, String BonusBall) {
        int matchNum = matchNum(Lotto, LastWeekLotto);
        int matchBonus = matchBonus(Lotto, BonusBall);
        return Rank.valueOf(matchNum, matchBonus == 1);
    }
}

