package domain;

import java.util.List;

public class LottoGame {

    public static int matchNum(List<String> Lotto, List<String> LastWeekLotto){
        int matchNum = 0;
        for (String n : Lotto) {
            if (LastWeekLotto.contains(n)) {
                    matchNum++;
            }
        }
        return matchNum;
    }

    public static int matchBonus(List<String> Lotto, String BonusBall){
        for (String n : Lotto) {
            if (n.equals(BonusBall)) {
                return 1;
            }
        }
        return 0;
    }
}

