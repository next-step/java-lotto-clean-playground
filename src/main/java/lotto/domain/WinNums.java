package lotto.domain;

import java.util.List;

import lotto.Constant;
import lotto.message.ErrorMessage;

public class WinNums {
    private final List<Integer> winNums;
    private final int bonusNum;

    public WinNums(List<Integer> winNums, int bonusNum) {
        this.winNums = winNums;
        this.bonusNum = bonusNum;
        validateNum();
        validateBonusNum();
    }

    public List<Integer> getWinNums() {
        return winNums;
    }

    public int getBonusNum() {
        return bonusNum;
    }

    private void validateNum() {
        for (int num : winNums) {
            if (num >= Constant.MIN_NUM && num <= Constant.MAX_NUM) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUM.getMessage());
            }
        }
    }

    private void validateBonusNum() {
        if (bonusNum >= Constant.MIN_NUM && bonusNum <= Constant.MAX_NUM) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUM.getMessage());
        }
    }
}
