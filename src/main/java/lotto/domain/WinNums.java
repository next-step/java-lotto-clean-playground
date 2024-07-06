package lotto.domain;

import java.util.List;

public class WinNums {
    private List<Integer> winNums;
    private int bonusNum;

    public WinNums(List<Integer> winNums, int bonusNum) {
        this.winNums = winNums;
        this.bonusNum = bonusNum;
    }

    public List<Integer> getWinNums() {
        return winNums;
    }

    public int getBonusNum() {
        return bonusNum;
    }
}
